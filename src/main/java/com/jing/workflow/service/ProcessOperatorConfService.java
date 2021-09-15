package com.jing.workflow.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jing.workflow.dao.WorkflowOperatorConfMapper;
import com.jing.workflow.domain.WorkflowOperatorConf;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/28 13:15
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Service
public class ProcessOperatorConfService extends ServiceImpl<WorkflowOperatorConfMapper, WorkflowOperatorConf> {

    @Resource
    private WorkflowOperatorConfMapper workflowOperatorConfMapper;

    public List<WorkflowOperatorConf> getOperatorConfsByProcessDefinitionId(String processDefinitionId){
        List<WorkflowOperatorConf> list = workflowOperatorConfMapper.selectList(new QueryWrapper<WorkflowOperatorConf>()
                .eq("PROCESS_DEFINITION_ID",processDefinitionId));
        return list;
    }

}
