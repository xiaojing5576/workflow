package com.jing.workflow.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jing.workflow.common.Constants;
import com.jing.workflow.dao.WorkflowReceiptInstanceMapper;
import com.jing.workflow.domain.WorkflowReceiptInstance;
import com.jing.workflow.domain.WorkflowOperatorConf;
import com.jing.workflow.model.ProcessInstanceCreateModel;
import com.jing.workflow.model.ReceiptProcessInstanceDetail;
import com.jing.workflow.model.UserResponseBody;
import com.jing.workflow.util.SnowFlakeUtil;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricProcessInstance;
import org.camunda.bpm.engine.history.HistoricTaskInstance;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: huangjingyan
 * @Date: 2021/5/7 17:32
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Service
public class ProcessReceiptInstanceService extends ServiceImpl<WorkflowReceiptInstanceMapper, WorkflowReceiptInstance> {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    @Resource
    private WorkflowReceiptInstanceMapper workFlowReceiptInstanceMapper;

    @Resource
    private ProcessTaskService processTaskService;

    @Resource
    private ProcessOperatorConfService processOperatorConfService;

    @Resource
    private RestTemplate restTemplate;

    @Value("${getStaffCodesByCompanyIdAndRoleId}")
    private String getStaffCodesByCompanyIdAndRoleId;

    @Value("${getStaffCodesByDepartmentRoleIds}")
    private String getStaffCodesByDepartmentRoleIds;

    @Value("${isStaffUnderNodeByStaffCode}")
    private String isStaffUnderNodeByStaffCode;

    public String createProcessInstance(ProcessInstanceCreateModel model){
        //1.camunda??????????????????
        Map<String,Object> params = new HashMap<>();
        params.put("amount",model.getAmount());
        params.put("applier",model.getApplier());
        params.put("company",model.getCompany());
        params.put("department",model.getDepartment());
        params.put("serviceType",model.getServiceType());
        params.put("receiptCode",model.getReceiptCode());
        //???????????????
        Map<String,List<String>> operators = new HashMap<>();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(model.getProcessDefinitionKey()).latestVersion().singleResult();
        List<WorkflowOperatorConf> operatorConfs = processOperatorConfService.getOperatorConfsByProcessDefinitionId(processDefinition.getId());
        operatorConfs.stream().forEach(x->{
            List<String> operator = new ArrayList<>();
            if(x.getOperatorSource().equals(Constants.PROCESS_OPERATOR_SOURCE.SPECIFIC.getCode())){
                //????????????
                operator.addAll(new ArrayList<String>(Arrays.asList(x.getCandidateOperator().split(","))));
            } else if(x.getOperatorSource().equals(Constants.PROCESS_OPERATOR_SOURCE.ORG_ROLE.getCode())){
                //??????????????????
                HttpHeaders headers = new HttpHeaders();
                MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
                headers.setContentType(type);
                headers.add("Accept", MediaType.APPLICATION_JSON.toString());
                headers.add("token","eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjE4MzM3MzEsInVzZXJuYW1lIjoiMjAwNjgxIn0.p6QhR3NyET4g73sdOCOkyDHU2ktUDFwkacISqn5Hzn4");
                //???????????????
                Map<String,String> requestParam = new HashMap<>();
                requestParam.put("parentDeptId",x.getCandidateOrgNodeCode());
                requestParam.put("roleId",x.getCandidateRoleCode());
                List<Map> requestBody = new ArrayList<>();
                requestBody.add(requestParam);
                HttpEntity<List> request = new HttpEntity<List>(requestBody, headers);
                ResponseEntity<UserResponseBody> response = restTemplate.postForEntity(getStaffCodesByDepartmentRoleIds,request,UserResponseBody.class);
                UserResponseBody responseBody = response.getBody();
                List<Integer> users = (List)responseBody.getData();
                operator.addAll(users.stream().map(String::valueOf).collect(Collectors.toList()));
            } else {
                //?????????????????????
                HttpHeaders headers = new HttpHeaders();
                MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
                headers.setContentType(type);
                headers.add("Accept", MediaType.APPLICATION_JSON.toString());
                headers.add("token","eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MjE4MzM3MzEsInVzZXJuYW1lIjoiMjAwNjgxIn0.p6QhR3NyET4g73sdOCOkyDHU2ktUDFwkacISqn5Hzn4");
//                headers.add("companyId",model.getCompany());
                List<String> requestBody = new ArrayList<>();
                requestBody.addAll(new ArrayList<String>(Arrays.asList(x.getCandidateRoleCode().split(","))));
                HttpEntity<List> request = new HttpEntity<List>(requestBody, headers);
                ResponseEntity<UserResponseBody> response = restTemplate.postForEntity(getStaffCodesByCompanyIdAndRoleId,request, UserResponseBody.class,model.getCompany());
                UserResponseBody responseBody = response.getBody();
                List<Integer> users = (List)responseBody.getData();
                operator.addAll(users.stream().map(String::valueOf).collect(Collectors.toList()));
            }
            //?????????????????????,????????????
            if(x.getPermitType().equals(Constants.PERMIT_TYPE.OR_SIGN.getCode())){
                if(operator.contains(model.getApplier())){
                    operator.clear();
                }
            }
            //????????????????????? ????????????????????????
            if(x.getOperatorNullableStrategy().equals(Constants.OPERATOR_NULLABLE_STRATEGY.TO_MANAGER.getCode())
                    && operator.size() == 0){
                //?????????????????????????????????
                operator.add("200681");
            }
            operator.remove(model.getApplier());
            operators.put(x.getOperatorCollectionParamKey(),operator);
        });
        params.putAll(operators);
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(model.getProcessDefinitionKey(),params);
        //2.?????????????????????
        WorkflowReceiptInstance receiptInstance = new WorkflowReceiptInstance();
        receiptInstance.setId(SnowFlakeUtil.nextId());
        receiptInstance.setProcessInstanceId(instance.getProcessInstanceId());
        receiptInstance.setProcessDefinitionId(instance.getProcessDefinitionId());
        receiptInstance.setProcessDefinitionKey(model.getProcessDefinitionKey());
        receiptInstance.setReceiptId(model.getReceiptId());
        receiptInstance.setReceiptCode(model.getReceiptCode());
        receiptInstance.setApplyer(model.getApplier());
        workFlowReceiptInstanceMapper.insert(receiptInstance);
        //3.???????????????????????????
        processTaskService.dealNextTask(instance.getProcessInstanceId(),model.getReceiptId());
        return instance.getProcessInstanceId();
    }


    public ReceiptProcessInstanceDetail getReceiptProcessInstanceDetail(String receiptId){

        WorkflowReceiptInstance workFlowReceiptInstance = workFlowReceiptInstanceMapper.selectOne(new QueryWrapper<WorkflowReceiptInstance>().eq("RECEIPT_ID",receiptId));

        ReceiptProcessInstanceDetail detail = new ReceiptProcessInstanceDetail();
        detail.setProcessInstanceId(workFlowReceiptInstance.getProcessInstanceId());
        detail.setReceiptId(receiptId);
        //?????????????????????????????????????????????
        ProcessInstance processInstanceStarting = runtimeService.createProcessInstanceQuery().processInstanceId(workFlowReceiptInstance.getProcessInstanceId()).singleResult();
        //????????????????????????
        HistoricProcessInstance processInstanceEnded = historyService.createHistoricProcessInstanceQuery().processInstanceId(workFlowReceiptInstance.getProcessInstanceId()).singleResult();

        if(processInstanceStarting == null && processInstanceEnded == null){
            return null;
        }
        //?????????
        if(processInstanceStarting != null){
            detail.setProcessInstanceStatus(Constants.PROCESS_INSTANCE_STATUS.AUDITING.getCode());
            if(processInstanceStarting.isSuspended()){
                detail.setProcessInstanceStatus(Constants.PROCESS_INSTANCE_STATUS.SUSPEND.getCode());
            }
            //????????????????????????
            List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceStarting.getProcessInstanceId()).list();
            detail.setCurrentExeUser(tasks.stream().map(Task::getAssignee).collect(Collectors.toList()));
        }else{
            detail.setProcessInstanceStatus(Constants.PROCESS_INSTANCE_STATUS.END.getCode());
            detail.setAuditTime(new Timestamp(processInstanceEnded.getEndTime().getTime()));
            List<HistoricTaskInstance> historyTaskInstances = historyService.createHistoricTaskInstanceQuery().processInstanceId(detail.getProcessInstanceId()).list();
            if(historyTaskInstances == null || historyTaskInstances.size() == 0){
                return detail;
            }
            HistoricTaskInstance lastTask = historyTaskInstances.stream().max(Comparator.comparing(x->x.getEndTime())).get();
            detail.setLastExeUser(lastTask.getAssignee());
        }
        return detail;
    }

}
