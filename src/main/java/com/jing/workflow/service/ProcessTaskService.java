package com.jing.workflow.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jing.workflow.common.Constants;
import com.jing.workflow.domain.WorkflowReceiptInstance;
import com.jing.workflow.domain.WorkflowOperatorConf;
import com.jing.workflow.domain.WorkflowTaskHistory;
import com.jing.workflow.util.SnowFlakeUtil;
import com.jing.workflow.model.*;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.runtime.ActivityInstance;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/28 14:18
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Service
public class ProcessTaskService {

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ProcessParamService processParamService;

    @Resource
    private ProcessParamConfService processParamConfService;

    @Resource
    private ProcessOperatorConfService operatorConfService;

    @Resource
    private ProcessReceiptInstanceService receiptInstanceService;

    @Resource
    private ProcessTaskHistoryService taskHistoryService;


    public Page<TaskVO> getWaitTodoTaskList(String assignee, Page page){
        List<Task> list = taskService.createTaskQuery().taskAssignee(assignee).active().listPage((int)page.getCurrent()-1,(int)page.getSize());
        Long total = 0L;
        total = taskService.createTaskQuery().taskAssignee(assignee).active().count();
        List<TaskVO> result = new ArrayList<>();
        list.forEach(x->{
            TaskDto taskDto = TaskDto.fromEntity(x);
            TaskVO task = new TaskVO();
            task.setTaskId(taskDto.getId());
            task.setName(taskDto.getName());
            task.setProcessDefinitionId(taskDto.getProcessDefinitionId());
            task.setProcessInstanceId(taskDto.getProcessInstanceId());
            task.setState(!taskDto.isSuspended());
            task.setCurrentAssignee(taskDto.getAssignee());
            List<WorkflowReceiptInstance> instance = receiptInstanceService.list(new QueryWrapper<WorkflowReceiptInstance>().eq("PROCESS_INSTANCE_ID",taskDto.getProcessInstanceId()));
            task.setReceiptId(instance.get(0).getReceiptId());
            result.add(task);
        });
        page.setTotal(total);
        page.setRecords(result);
        return page;
    }

    public TaskDetailVO getTaskDetail(String taskId){
        TaskDetailVO result = new TaskDetailVO();
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        result.setTaskId(task.getId());
        result.setTaskKey(task.getTaskDefinitionKey());
        result.setName(task.getName());
        result.setProcessDefinitionId(task.getProcessDefinitionId());
        result.setProcessInstanceId(task.getProcessInstanceId());
        result.setCurrentAssignee(task.getAssignee());
        result.setState(!task.isSuspended());
        List<ProcessParamDetailModel> paramDefinitions = processParamService.getProcessParamDetails(task.getProcessDefinitionId());
        result.setParamDefinitions(paramDefinitions);
        List<ProcessParamConfDetailModel> paramConfs = processParamConfService.getParamConfDetailList(task.getProcessDefinitionId());
        result.setParamConfigs(paramConfs);
        Map<String,Object> paramValues = taskService.getVariables(task.getId());
        result.setParamValues(paramValues);

        String parentTaskId = task.getParentTaskId();
        List<TaskSimpleModel> ancestorTasks = new ArrayList<>();
        while(parentTaskId!= null){
            HistoricTaskInstance ancestorTask = historyService.createHistoricTaskInstanceQuery().taskId(parentTaskId).singleResult();
            if(ancestorTask !=null){
                TaskSimpleModel model = new TaskSimpleModel();
                model.setTaskId(parentTaskId);
                model.setParentTaskId(ancestorTask.getParentTaskId());
                model.setAssignee(ancestorTask.getAssignee());
                model.setOpinion(true);
                model.setTaskKey(ancestorTask.getTaskDefinitionKey());
                ancestorTasks.add(model);
            }
        }
        result.setAncestorTasks(ancestorTasks);
        return result;
    }

    public Page<TaskVO> getDoneTaskList(String assignee,Page page){
        List<TaskVO> result = new ArrayList<>();
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().taskAssignee(assignee).finished()
                .listPage((int)page.getCurrent(),(int)page.getSize());
        list.forEach(x->{
            TaskVO task = new TaskVO();
            task.setTaskId(x.getProcessInstanceId());
            task.setName(x.getName());
            task.setState(true);
            task.setCurrentAssignee(x.getAssignee());
            task.setProcessInstanceId(x.getProcessInstanceId());
            task.setProcessDefinitionId(x.getProcessDefinitionId());
            result.add(task);
        });
        page.setRecords(result);
        return page;
    }

    public String completeTask(TaskCompleteModel model){
        Task task = taskService.createTaskQuery().taskId(model.getTaskId()).singleResult();
        Map<String,Object> params = taskService.getVariables(task.getId());
        String processInstanceId = task.getProcessInstanceId();
        //添加批注
        if(StringUtils.isNotBlank(model.getComments())){
            taskService.createComment(task.getId(),null,model.getComments());
        }
        //审批通过
        if(model.getOpinion()){
            taskService.complete(model.getTaskId(),params);
            WorkflowTaskHistory currentTask = taskHistoryService.getOne(new QueryWrapper<WorkflowTaskHistory>()
                            .eq("PROCESS_INSTANCE_ID",processInstanceId)
                            .eq("TASK_ID",task.getId())
                            .eq("TASK_DEFINITION_KEY",task.getTaskDefinitionKey()));
            currentTask.setStatus(Constants.TASK_STATUS.COMPLETE.getCode());
            currentTask.setOpinion(Constants.TASK_OPINION.AGREE.getCode());
            currentTask.setComments(model.getComments());
            currentTask.setEndTime(new Timestamp(System.currentTimeMillis()));
            taskHistoryService.updateById(currentTask);
            //处理或签(兄弟节点状态修改)
            WorkflowOperatorConf nodeOperatorConf = operatorConfService.getOne(new QueryWrapper<WorkflowOperatorConf>()
                            .eq("PROCESS_DEFINITION_ID",currentTask.getProcessDefinitionId())
                            .eq("PROCESS_DEFINITION_TASK_ID",task.getTaskDefinitionKey()));
            if(nodeOperatorConf.getPermitType().equals(Constants.PERMIT_TYPE.OR_SIGN.getCode())) {
                    taskHistoryService.update(new UpdateWrapper<WorkflowTaskHistory>()
                            .eq("PROCESS_INSTANCE_ID", processInstanceId)
                            .ne("TASK_ID", task.getId())
                            .eq("TASK_DEFINITION_KEY", task.getTaskDefinitionKey())
                            .set("STATUS",Constants.TASK_STATUS.COMPLETE.getCode())
                            .set("OPINION",Constants.TASK_OPINION.AGREE.getCode())
                            .set("COMMENTS","自动同意")
                            .set("END_TIME",new Timestamp(System.currentTimeMillis())));
            }
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(model.getProcessInstanceId()).list();
            if(tasks != null && tasks.size() != 0){
                if(!tasks.get(0).getTaskDefinitionKey().equals(currentTask.getTaskDefinitionKey())){
                    //处理下一个节点，不隶属同一个节点(已经流转至下一个节点)
                    this.dealNextTask(processInstanceId,currentTask.getReceiptId());
                }
            }
        } else {
            //挂起流程
            runtimeService.suspendProcessInstanceById(processInstanceId);
            WorkflowTaskHistory currentTask = new WorkflowTaskHistory();
            currentTask = taskHistoryService.getOne(new QueryWrapper<WorkflowTaskHistory>()
                    .eq("PROCESS_INSTANCE_ID",processInstanceId)
                    .eq("TASK_ID",task.getId())
                    .eq("TASK_DEFINITION_KEY",task.getTaskDefinitionKey()));
            currentTask.setStatus(Constants.TASK_STATUS.COMPLETE.getCode());
            currentTask.setOpinion(Constants.TASK_OPINION.REFUSE.getCode());
            currentTask.setComments(model.getComments());
            currentTask.setEndTime(new Timestamp(System.currentTimeMillis()));
            taskHistoryService.updateById(currentTask);

        }
        return processInstanceId;
    }

    public void dealNextTask(String processInstanceId,String receiptId){
        ProcessInstance processInstanceStarting = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processInstanceStarting.getProcessDefinitionId()).singleResult();
        //任务已经结束
        if(processInstanceStarting == null){
            return ;
        }
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceStarting.getProcessInstanceId()).list();
        for(Task x : tasks){
            WorkflowTaskHistory nextTask = new WorkflowTaskHistory();
            nextTask.setId(SnowFlakeUtil.nextId());
            nextTask.setProcessDefinitionId(processDefinition.getId());
            nextTask.setProcessInstanceId(processInstanceStarting.getProcessInstanceId());
            nextTask.setProcessDefinitionKey(processDefinition.getKey());
            nextTask.setReceiptId(receiptId);
            nextTask.setTaskDefinitionKey(x.getTaskDefinitionKey());
            nextTask.setTaskId(x.getId());
            nextTask.setStatus(Constants.TASK_STATUS.UN_COMPLETE.getCode());
            nextTask.setOpinion(Constants.TASK_OPINION.UN_DO.getCode());
            nextTask.setOperators(x.getAssignee());
            nextTask.setStartTime(new Timestamp(System.currentTimeMillis()));
            taskHistoryService.save(nextTask);
            //若已经在前面出现过，则直接完成该任务
        }
        List<WorkflowTaskHistory> completedTasks = taskHistoryService.list(new QueryWrapper<WorkflowTaskHistory>()
                .eq("PROCESS_INSTANCE_ID", processInstanceId)
                .eq("STATUS", Constants.TASK_STATUS.COMPLETE.getCode())
                .and(x->x.ne("COMMENTS", "自动同意").or().isNull("COMMENTS")));

        if(completedTasks == null ||completedTasks.size() == 0){
            return;
        }
        List<String> doneUsers = completedTasks.stream().map(WorkflowTaskHistory::getOperators).collect(Collectors.toList());
        for(Task x : tasks) {
            if (doneUsers != null && doneUsers.contains(x.getAssignee())) {
                TaskCompleteModel completeModel = new TaskCompleteModel();
                completeModel.setTaskId(x.getId());
                completeModel.setUserId(x.getAssignee());
                completeModel.setOpinion(true);
                completeModel.setProcessInstanceId(processInstanceId);
                completeModel.setProcessDefinitionId(x.getProcessDefinitionId());
                completeModel.setParams(taskService.getVariables(x.getId()));
                completeModel.setComments("自动同意");
                this.completeTask(completeModel);
            }
        }
    }

    private String getInstanceIdForActivity(ActivityInstance activityInstance, String activityId) {
        ActivityInstance instance = getChildInstanceForActivity(activityInstance, activityId);
        if (instance != null) {
            return instance.getId();
        }
        return null;
    }

    private ActivityInstance getChildInstanceForActivity(ActivityInstance activityInstance, String activityId) {
        if (activityId.equals(activityInstance.getActivityId())) {
            return activityInstance;
        }
        for (ActivityInstance childInstance : activityInstance.getChildActivityInstances()) {
            ActivityInstance instance = getChildInstanceForActivity(childInstance, activityId);
            if (instance != null) {
                return instance;
            }
        }
        return null;
    }

    public String transferTask(String taskId,String assignee){
        taskService.setAssignee(taskId,assignee);
        return "success";
    }

    public String addSigner(String taskId, List<String> addSigners){
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        ProcessInstance instance = runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessDefinitionId()).singleResult();
        WorkflowOperatorConf workflowOperatorConf = operatorConfService.getOne(new QueryWrapper<WorkflowOperatorConf>()
                .eq("PROCESS_DEFINITION_ID",instance.getProcessDefinitionId())
                .eq("PROCESS_DEFINITION_TASK_ID",task.getTaskDefinitionKey()));
        WorkflowReceiptInstance receiptInstance = receiptInstanceService.getOne(new QueryWrapper<WorkflowReceiptInstance>()
                .eq("PROCESS_INSTANCE_ID",instance.getId())
                .eq("PROCESS_DEFINITION_ID",instance.getProcessDefinitionId()));
        //获取当前任务节点的执行人集合
        addSigners.forEach(x->{
            runtimeService.createProcessInstanceModification(task.getProcessDefinitionId())
                    .startBeforeActivity(task.getTaskDefinitionKey())
                    .setVariable("assignee",x)
                    .setVariable(workflowOperatorConf.getOperatorParamKey(),x)
                    .execute();
            //3.添加执行记录表
            Task userTask = taskService.createTaskQuery().taskAssignee(x)
                    .processInstanceId(instance.getProcessInstanceId())
                    .taskDefinitionKey(task.getTaskDefinitionKey())
                    .singleResult();
            WorkflowTaskHistory nextTask = new WorkflowTaskHistory();
            nextTask.setId(SnowFlakeUtil.nextId());
            nextTask.setProcessDefinitionId(instance.getProcessDefinitionId());
            nextTask.setProcessInstanceId(instance.getId());
            nextTask.setProcessDefinitionKey(receiptInstance.getProcessDefinitionKey());
            nextTask.setReceiptId(receiptInstance.getReceiptId());
            nextTask.setTaskDefinitionKey(userTask.getTaskDefinitionKey());
            nextTask.setTaskId(userTask.getId());
            nextTask.setStatus(Constants.TASK_STATUS.UN_COMPLETE.getCode());
            nextTask.setOpinion(Constants.TASK_OPINION.UN_DO.getCode());
            nextTask.setOperators(x);
            nextTask.setStartTime(new Timestamp(System.currentTimeMillis()));
            taskHistoryService.save(nextTask);
        });
        return "success";
    }

}
