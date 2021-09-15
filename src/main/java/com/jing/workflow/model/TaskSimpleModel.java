package com.jing.workflow.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/28 15:31
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@ApiModel("简要任务信息")
@Data
public class TaskSimpleModel {
    @ApiModelProperty("任务Id")
    private String taskId;
    @ApiModelProperty("任务节点key")
    private String taskKey;
    @ApiModelProperty("任务执行人")
    private String assignee;
    @ApiModelProperty("父节点任务Id")
    private String parentTaskId;
    @ApiModelProperty("父节点任务key")
    private String parentTaskKey;
    @ApiModelProperty("审批意见")
    private Boolean opinion;
}
