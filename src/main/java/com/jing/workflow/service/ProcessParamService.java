package com.jing.workflow.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jing.workflow.dao.WorkflowParamMapper;
import com.jing.workflow.domain.WorkflowParam;
import com.jing.workflow.model.ProcessParamDetailModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/28 13:16
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Service
public class ProcessParamService extends ServiceImpl<WorkflowParamMapper, WorkflowParam> {

    @Resource
    private WorkflowParamMapper workflowParamMapper;

    public List<ProcessParamDetailModel> getProcessParamDetails(String processDefinitionId){
        List<ProcessParamDetailModel> result = new ArrayList<>();
        List<WorkflowParam> params = workflowParamMapper.selectList(new QueryWrapper<WorkflowParam>()
                .eq("PROCESS_DEFINITION_ID",processDefinitionId));
        params.forEach(x->{
            ProcessParamDetailModel model = new ProcessParamDetailModel();
            BeanUtils.copyProperties(x,model);
            result.add(model);
        });
        return result;
    }

    public List<WorkflowParam> getParamByProcessDefinitionId(String processDefinitionId){
        List<WorkflowParam> list = workflowParamMapper.selectList(new QueryWrapper<WorkflowParam>()
                .eq("PROCESS_DEFINITION_ID",processDefinitionId));
        return list;
    }
}
