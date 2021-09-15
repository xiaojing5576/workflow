package com.jing.workflow.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jing.workflow.common.response.ResponseResult;
import com.jing.workflow.common.response.ResultCode;
import com.jing.workflow.domain.*;
import com.jing.workflow.service.ProcessDefinitionService;
import com.jing.workflow.util.KeyGeneratorUtil;
import com.jing.workflow.model.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 14:09
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Api("审批流模板操作")
@RestController
@RequestMapping("/process/definition")
public class ProcessDefinitionController {

    @Resource
    private ProcessDefinitionService processDefinitionService;

    @ApiOperation("流程基本信息保存")
    @PostMapping("/baseinfo/save")
    public ResponseResult<String> saveBaseInfo(@RequestBody ProcessBaseInfoSaveModel model){
        processDefinitionService.saveBaseInfo(model);
        return new ResponseResult<>(ResultCode.OK,"success");
    }

    @ApiOperation("流程基础表参数保存")
    @PostMapping("/param/save")
    public ResponseResult<String> saveParam(@RequestBody ProcessParamSaveModel model){
        processDefinitionService.saveParam(model);
        return new ResponseResult<>(ResultCode.OK,"success");
    }

    @ApiOperation("流程表单执行权限保存")
    @PostMapping("/param/right/save")
    public ResponseResult<String> saveParamRight(@RequestBody ProcessParamConfSaveModel model){
        processDefinitionService.saveParamConf(model);
        return new ResponseResult<>(ResultCode.OK,"success");
    }

    @ApiOperation("流程节点执行人设置保存")
    @PostMapping("/operator/save")
    public ResponseResult<String> saveOperator(@RequestBody ProcessOperatorSaveModel model){
        processDefinitionService.saveOperatorConf(model);
        return new ResponseResult<>(ResultCode.OK,"success");
    }

    @ApiOperation("流程基础表参数初始化（paramkey生成）")
    @PostMapping("/param/init")
    public ResponseResult<List<ProcessParamDetailModel>> paramInit(@RequestBody List<ProcessParamDetailModel> params){
        params.forEach(x->{
            x.setParamKey(KeyGeneratorUtil.getKey());
        });
        return new ResponseResult<>(ResultCode.OK,params);
    }

    @ApiOperation("执行人配置初始化（operatorParamKey生成）")
    @PostMapping("/operator/config/init")
    public ResponseResult<List<ProcessOperatorSimpleModel>> operatorConfigInit(@RequestBody List<ProcessOperatorSimpleModel> operatorConfigs){
        operatorConfigs.forEach(x->{
            x.setOperatorParamKey(KeyGeneratorUtil.getKey());
            x.setOperatorCollectionParamKey(KeyGeneratorUtil.getKey());
        });
        return new ResponseResult<>(ResultCode.OK,operatorConfigs);
    }

    @ApiOperation("审批流基本信息processDefinitionKey生成")
    @PostMapping("/init")
    public ResponseResult<ProcessBaseInfoSaveModel> processDefinitionInit(@RequestBody ProcessBaseInfoSaveModel model){
        model.setProcessDefinitionKey(KeyGeneratorUtil.getProcessKey());
        return new ResponseResult<>(ResultCode.OK,model);
    }

    @ApiOperation("审批流发布")
    @PostMapping("/deploy/xml")
    public ResponseResult<String> deployProcessByXml(@RequestBody ProcessDefinitionCreateModel model) throws XMLStreamException {
        String processDefinitionId = processDefinitionService.deployProcess(model);
        return new ResponseResult<>(ResultCode.OK,processDefinitionId);
    }

    @ApiOperation("查询审批流基本信息")
    @GetMapping("/baseinfo/query")
    public ResponseResult<ProcessBaseInfoDetailModel> queryProcessDefinitionBaseInfo(@RequestParam String processDefinitionKey){
        WorkflowDefinitionBaseInfo baseInfo = processDefinitionService.getProcessDefinitionBaseInfo(processDefinitionKey);
        ProcessBaseInfoDetailModel model = new ProcessBaseInfoDetailModel();
        BeanUtils.copyProperties(baseInfo,model);
        String fitOrgCodes = baseInfo.getFitOrgCodes();

        List<String> codes = new ArrayList<>();
        if(StringUtils.isNotBlank(fitOrgCodes)){
            codes = new ArrayList<>(Arrays.asList(fitOrgCodes.split(",")));
        }
        model.setFitOrgCodes(codes);
        return new ResponseResult<>(ResultCode.OK,model);
    }

    @ApiOperation("查询审批流基础表信息")
    @GetMapping("/param/list/query")
    public ResponseResult<List<WorkflowParam>> queryParamList(@RequestParam String processDefinitionId){
        List<WorkflowParam> list = processDefinitionService.getProcessDefinitionParam(processDefinitionId);
        return new ResponseResult<>(ResultCode.OK,list);
    }

    @ApiOperation("查询基础表参数配置信息（需全部返回）")
    @GetMapping("/param/config/query")
    public ResponseResult<List<WorkflowParamConf>> queryParamConfig(@RequestParam String processDefinitionId){
        List<WorkflowParamConf> list = processDefinitionService.getProcessDefinitionParamConf(processDefinitionId);
        return new ResponseResult<>(ResultCode.OK,list);
    }

    @ApiOperation("查询执行人配置信息（需全部返回）")
    @GetMapping("/operator/config/query")
    public ResponseResult<List<ProcessOperatorDetailModel>> queryOperatorConfig(@RequestParam String processDefinitionId){
        List<ProcessOperatorDetailModel> list = processDefinitionService.getProcessDefinitionOperatorConf(processDefinitionId);
        return new ResponseResult<>(ResultCode.OK,list);
    }

    @ApiOperation("查询流程定义bpmn xml")
    @GetMapping("/query/xml")
    public ResponseResult<String> queryProcessDefinitionXml(@RequestParam String processDefinitionId) throws IOException {
        String xmlContent = processDefinitionService.queryProcessDefinitionXml(processDefinitionId);
        return new ResponseResult<>(ResultCode.OK,xmlContent);
    }

    @ApiOperation("查询流程定义列表(可分页，page，size)")
    @GetMapping("/query/list")
    public ResponseResult<Page<ProcessDefinitionSimpleModel>> queryProcessDefinitionList(@ApiIgnore Pageable pageable,String name,String groupKey){
        Page<ProcessDefinitionSimpleModel> page = new Page<>(pageable.getPageNumber()==0?1:pageable.getPageNumber(),pageable.getPageSize());
        page = processDefinitionService.getBaseInfoList(page,name,groupKey);
        return new ResponseResult<>(ResultCode.OK,page);
    }

    @ApiOperation("保存流程定义条件json")
    @PostMapping("/condition/save")
    public ResponseResult<String> saveProcessDefinitionCondition(@RequestBody ProcessConditionSaveModel model){
        String result = processDefinitionService.saveCondition(model);
        return new ResponseResult<>(ResultCode.OK,result);
    }

    @ApiOperation("查询流程定义条件json")
    @GetMapping("/condition/query")
    public ResponseResult<WorkflowDefinitionCondition> getWorkFlowCondition(String processDefinitionId){
        WorkflowDefinitionCondition condition = processDefinitionService.getCondition(processDefinitionId);
        return new ResponseResult<>(ResultCode.OK,condition);
    }

    @ApiOperation("查询流程分组信息列表")
    @GetMapping("/group/list")
    public ResponseResult<List<WorkflowDefinitionGroup>> getGroupList(){
        List<WorkflowDefinitionGroup> list = processDefinitionService.getGroupList();
        return new ResponseResult<>(ResultCode.OK,list);
    }
}
