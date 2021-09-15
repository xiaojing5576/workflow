package com.jing.workflow.controller;

import com.jing.workflow.common.response.ResponseResult;
import com.jing.workflow.common.response.ResultCode;
import com.jing.workflow.model.ProcessInstanceCreateModel;
import com.jing.workflow.model.ReceiptProcessInstanceDetail;
import com.jing.workflow.service.ProcessReceiptInstanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 16:38
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Api("流程实例接口")
@RequestMapping("/process/instance")
@RestController
public class ProcessInstanceController {

    @Resource
    private ProcessReceiptInstanceService receiptInstanceService;

    @ApiOperation("创建流程实例")
    @PostMapping("/create")
    public ResponseResult<String> createProcessInstance(@RequestBody ProcessInstanceCreateModel model){
        String processInstanceId = receiptInstanceService.createProcessInstance(model);
        return new ResponseResult<>(ResultCode.OK,processInstanceId);
    }

    @ApiOperation("查询流程实例详情")
    @PostMapping("/detail/query")
    public ResponseResult<List<ReceiptProcessInstanceDetail>> queryInstanceDetail(@RequestBody List<String> receiptIds){
        List<ReceiptProcessInstanceDetail> details = new ArrayList<>();
        if(receiptIds == null ||receiptIds.size() == 0){
            return new ResponseResult<>(ResultCode.OK,details);
        }
        receiptIds.forEach(x->{
            details.add(receiptInstanceService.getReceiptProcessInstanceDetail(x));
        });
        return new ResponseResult<>(ResultCode.OK,details);
    }



}
