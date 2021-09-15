package com.jing.workflow.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 17:58
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@ApiModel
@Data
public class TaskCompleteModel {
    @ApiModelProperty("任务Id")
    private String taskId;
    @ApiModelProperty("审批人ID")
    private String userId;
    @ApiModelProperty("审批意见：通过true，驳回false")
    private Boolean opinion;
    @ApiModelProperty("流程定义id")
    private String processDefinitionId;
    @ApiModelProperty("审批流实例id")
    private String processInstanceId;
    @ApiModelProperty("参数列表")
    private Map<String,Object> params;
    @ApiModelProperty("批注")
    private String comments;
}
