package com.jing.workflow.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jing.workflow.dao.WorkflowParamConfMapper;
import com.jing.workflow.domain.WorkflowParamConf;
import com.jing.workflow.model.ProcessParamConfDetailModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/28 13:17
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Service
public class ProcessParamConfService extends ServiceImpl<WorkflowParamConfMapper, WorkflowParamConf> {

    @Resource
    private WorkflowParamConfMapper workflowParamConfMapper;

    public List<ProcessParamConfDetailModel> getParamConfDetailList(String processDefinitionId){
        List<ProcessParamConfDetailModel> result = new ArrayList<>();
        List<WorkflowParamConf> list = workflowParamConfMapper.selectList(new QueryWrapper<WorkflowParamConf>()
                .eq("PROCESS_DEFINITION_ID",processDefinitionId));
        list.forEach(x->{
            ProcessParamConfDetailModel model = new ProcessParamConfDetailModel();
            BeanUtils.copyProperties(x,model);
            result.add(model);
        });
        return result;
    }

    public List<WorkflowParamConf> getParamConfList(String processDefinitionId){
        List<WorkflowParamConf> list = workflowParamConfMapper.selectList(new QueryWrapper<WorkflowParamConf>()
                .eq("PROCESS_DEFINITION_ID",processDefinitionId));
        return list;
    }

}
