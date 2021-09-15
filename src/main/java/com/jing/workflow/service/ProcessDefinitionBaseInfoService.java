package com.jing.workflow.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jing.workflow.dao.WorkflowDefinitionBaseInfoMapper;
import com.jing.workflow.domain.WorkflowDefinitionBaseInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/28 13:08
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Service
public class ProcessDefinitionBaseInfoService  extends ServiceImpl<WorkflowDefinitionBaseInfoMapper, WorkflowDefinitionBaseInfo> {

    @Resource
    private WorkflowDefinitionBaseInfoMapper workflowDefinitionBaseInfoMapper;


    public WorkflowDefinitionBaseInfo getBaseInfoByProcessDefinitionKey(String processDefinitionKey){
        List<WorkflowDefinitionBaseInfo> list = workflowDefinitionBaseInfoMapper.selectList(new QueryWrapper<WorkflowDefinitionBaseInfo>().eq("PROCESS_DEFINITION_KEY",processDefinitionKey));
        if(list == null ||list.size() == 0){
            return null;
        }
        return list.get(0);
    }

    public Page getPageList(Page page){
        page = workflowDefinitionBaseInfoMapper.selectPage(page,new QueryWrapper<WorkflowDefinitionBaseInfo>());
        return page;
    }


}
