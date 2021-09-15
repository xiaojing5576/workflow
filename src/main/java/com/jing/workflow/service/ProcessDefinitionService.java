package com.jing.workflow.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jing.workflow.common.Constants;
import com.jing.workflow.common.exception.ServiceException;
import com.jing.workflow.common.response.ResultCode;
import com.jing.workflow.domain.*;
import com.jing.workflow.util.SnowFlakeUtil;
import com.jing.workflow.model.*;
import org.apache.commons.lang3.StringUtils;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.stream.XMLStreamException;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/28 16:39
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Service
public class ProcessDefinitionService {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ProcessDefinitionBaseInfoService baseInfoService;

    @Resource
    private ProcessParamService paramService;

    @Resource
    private ProcessParamConfService paramConfService;

    @Resource
    private ProcessOperatorConfService operatorConfService;

    @Resource
    private ProcessDefinitionConditionService conditionService;

    @Resource
    private ProcessDefinitionGroupService groupService;

    public String deployProcess(ProcessDefinitionCreateModel model) throws XMLStreamException {
        InputStream inputStream = new ByteArrayInputStream(model.getXmlContent().getBytes());
        //创建转换对象
        //读取xml文件
//        XMLInputFactory factory = XMLInputFactory.newInstance();
        Deployment deployment = repositoryService.createDeployment()
                .addInputStream(model.getProcessDefinitionKey() +"dynamic-model.bpmn",inputStream)
                .deploy();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        if(processDefinition == null){
            throw new ServiceException(ResultCode.PROCESS_DEFINITION_INVALID);
        }
        return processDefinition.getId();
    }

    public String queryProcessDefinitionXml(String processDefinitionId) throws IOException {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        InputStream inputStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getResourceName());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder result = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    public void saveBaseInfo(ProcessBaseInfoSaveModel model){
        WorkflowDefinitionBaseInfo info = new WorkflowDefinitionBaseInfo();
        BeanUtils.copyProperties(model,info);
        Long version = repositoryService.createProcessDefinitionQuery().processDefinitionKey(model.getProcessDefinitionKey()).count();
        info.setProcessDefinitionVersion(String.valueOf(version));
        info.setStatus(Constants.PROCESS_DEFINITION_STATUS.STARTING.getCode());
        List<String> fitOrgCodes = model.getFitOrgCodes();
        if(fitOrgCodes != null && fitOrgCodes.size() > 0){
            info.setFitOrgCodes(fitOrgCodes.stream().collect(Collectors.joining(",")));
        }
        WorkflowDefinitionBaseInfo exist = baseInfoService.getOne(new QueryWrapper<WorkflowDefinitionBaseInfo>().eq("PROCESS_DEFINITION_KEY",model.getProcessDefinitionKey()));
        if(exist != null){
            info.setId(exist.getId());
            baseInfoService.updateById(info);
        }else {
            info.setId(SnowFlakeUtil.nextId());
            info.setUpdateBy("sys");
            baseInfoService.save(info);
        }

    }

    public void saveParam(ProcessParamSaveModel model){
        List<WorkflowParam> params = new ArrayList<>();
        model.getParams().forEach(x->{
            WorkflowParam param = new WorkflowParam();
            param.setId(SnowFlakeUtil.nextId());
            BeanUtils.copyProperties(x,param);
            param.setProcessDefinitionId(model.getProcessDefinitionId());
            param.setProcessDefinitionKey(model.getProcessDefinitionKey());
            Long version = repositoryService.createProcessDefinitionQuery().processDefinitionKey(model.getProcessDefinitionKey()).count();
            param.setProcessDefinitionVersion(String.valueOf(version));
            params.add(param);
        });
        paramService.saveBatch(params);
    }

    public void saveParamConf(ProcessParamConfSaveModel model){
        List<WorkflowParamConf> paramConfs = new ArrayList<>();
        model.getParamConfigs().forEach(x->{
            WorkflowParamConf paramConf = new WorkflowParamConf();
            BeanUtils.copyProperties(x,paramConf);
            paramConf.setId(SnowFlakeUtil.nextId());
            paramConf.setProcessDefinitionId(model.getProcessDefinitionId());
            paramConf.setProcessDefinitionKey(model.getProcessDefinitionKey());
            Long version = repositoryService.createProcessDefinitionQuery().processDefinitionKey(model.getProcessDefinitionKey()).count();
            paramConf.setProcessDefinitionVersion(String.valueOf(version));
            paramConfs.add(paramConf);
        });
        paramConfService.saveBatch(paramConfs);
    }

    public void saveOperatorConf(ProcessOperatorSaveModel model){
        List<WorkflowOperatorConf> operatorConfs = new ArrayList<>();
        model.getOperatorConfs().forEach(x->{
            WorkflowOperatorConf operatorConf = new WorkflowOperatorConf();
            operatorConf.setId(SnowFlakeUtil.nextId());
            BeanUtils.copyProperties(x,operatorConf);
            if(x.getCandidateOperator() != null && x.getCandidateOperator().size() > 0){
                operatorConf.setCandidateOperator(x.getCandidateOperator().stream().collect(Collectors.joining(",")));
            }
            if(x.getCandidateOrgNodeCode() != null && x.getCandidateOrgNodeCode().size() > 0){
                operatorConf.setCandidateOrgNodeCode(x.getCandidateOrgNodeCode().stream().collect(Collectors.joining(",")));
            }
            if(x.getCandidateRoleCode() != null && x.getCandidateRoleCode().size() > 0){
                operatorConf.setCandidateRoleCode(x.getCandidateRoleCode().stream().collect(Collectors.joining(",")));
            }
            if(x.getRenderCandidateOperator() != null && x.getRenderCandidateOperator().size() > 0){
                operatorConf.setRenderCandidateOperator(x.getRenderCandidateOperator().stream().collect(Collectors.joining(",")));
            }
            operatorConf.setProcessDefinitionId(model.getProcessDefinitionId());
            operatorConf.setProcessDefinitionKey(model.getProcessDefinitionKey());
            Long version = repositoryService.createProcessDefinitionQuery().processDefinitionKey(model.getProcessDefinitionKey()).count();
            operatorConf.setProcessDefinitionVersion(String.valueOf(version));
            operatorConfs.add(operatorConf);
        });
        operatorConfService.saveBatch(operatorConfs);
    }

    public WorkflowDefinitionBaseInfo getProcessDefinitionBaseInfo(String processDefinitionKey){
        return baseInfoService.getBaseInfoByProcessDefinitionKey(processDefinitionKey);
    }

    public List<ProcessOperatorDetailModel> getProcessDefinitionOperatorConf(String processDefinitionId){
        List<WorkflowOperatorConf> list = operatorConfService.getOperatorConfsByProcessDefinitionId(processDefinitionId);
        List<ProcessOperatorDetailModel> result = new ArrayList<>();
        list.forEach(x->{
            ProcessOperatorDetailModel model = new ProcessOperatorDetailModel();
            BeanUtils.copyProperties(x,model);
            if(StringUtils.isNotBlank(x.getCandidateOperator())){
                model.setCandidateOperator(new ArrayList<>(Arrays.asList(x.getCandidateOperator().split(","))));
            }
            if(StringUtils.isNotBlank(x.getCandidateOrgNodeCode())){
                model.setCandidateOrgNodeCode(new ArrayList<>(Arrays.asList(x.getCandidateOrgNodeCode().split(","))));
            }
            if(StringUtils.isNotBlank(x.getCandidateRoleCode())){
                model.setCandidateRoleCode(new ArrayList<>(Arrays.asList(x.getCandidateRoleCode().split(","))));
            }
            if(StringUtils.isNotBlank(x.getRenderCandidateOperator())){
                model.setRenderCandidateOperator(new ArrayList<>(Arrays.asList(x.getRenderCandidateOperator().split(","))));
            }
            result.add(model);
        });
        return result;
    }

    public List<WorkflowParam> getProcessDefinitionParam(String processDefinitionId){
        return paramService.getParamByProcessDefinitionId(processDefinitionId);
    }

    public List<WorkflowParamConf> getProcessDefinitionParamConf(String processDefinitionId){
        return paramConfService.getParamConfList(processDefinitionId);
    }

    public Page<ProcessDefinitionSimpleModel> getBaseInfoList(Page page,String name,String groupKey){
        page = baseInfoService.page(page,new QueryWrapper<WorkflowDefinitionBaseInfo>()
                .like(StringUtils.isNotBlank(name),"NAME",name)
                .eq(StringUtils.isNotBlank(groupKey),"GROUP_KEY",groupKey));
        List<WorkflowDefinitionBaseInfo> records = page.getRecords();
        List<ProcessDefinitionSimpleModel> result = new ArrayList<>();
        records.forEach(x->{
            ProcessDefinitionSimpleModel model = new ProcessDefinitionSimpleModel();
            BeanUtils.copyProperties(x,model);
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey(model.getProcessDefinitionKey()).latestVersion().singleResult();
            model.setProcessDefinitionId(processDefinition.getId());
            WorkflowDefinitionGroup group = groupService.getOne(new QueryWrapper<WorkflowDefinitionGroup>().eq("GROUP_KEY",x.getGroupKey()));
            model.setGroupName(group.getGroupName());
            result.add(model);
        });
        page.setRecords(result);
        return page;
    }

    public String saveCondition(ProcessConditionSaveModel model){
        WorkflowDefinitionCondition condition = new WorkflowDefinitionCondition();
        BeanUtils.copyProperties(model,condition);
        condition.setId(SnowFlakeUtil.nextId());
        Long version = repositoryService.createProcessDefinitionQuery().processDefinitionKey(model.getProcessDefinitionKey()).count();
        condition.setProcessDefinitionVersion(String.valueOf(version));
        conditionService.save(condition);
        return "success";
    }

    public WorkflowDefinitionCondition getCondition(String processDefinitionId){
        List<WorkflowDefinitionCondition> list = conditionService.list(new QueryWrapper<WorkflowDefinitionCondition>().eq("PROCESS_DEFINITION_ID",processDefinitionId));
        if(list == null || list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    public List<WorkflowDefinitionGroup> getGroupList(){
        List<WorkflowDefinitionGroup> list = groupService.list();
        return list;
    }

}
