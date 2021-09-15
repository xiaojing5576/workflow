package com.jing.workflow.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: huangjingyan
 * @Date: 2021/5/7 14:25
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
public class ProcessInstanceCreateModel {
    @ApiModelProperty("流程定义Key")
    private String processDefinitionKey;
    @ApiModelProperty("单据类型")
    private String receiptCode;
    @ApiModelProperty("发起人")
    private String applier;
    @ApiModelProperty("所在部门")
    private String department;
    @ApiModelProperty("所在公司")
    private String company;
    @ApiModelProperty("支付金额")
    private String amount;
    @ApiModelProperty("业务类型")
    private String serviceType;
    @ApiModelProperty("单据实例id")
    private String receiptId;
//    @ApiModelProperty("执行人列表")
//    private Map<String, List<String>> operators;

}
