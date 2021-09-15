package com.jing.workflow.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;


/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 17:31
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel
public class TaskVO {
    @ApiModelProperty("任务id")
    private String taskId;
    @ApiModelProperty("任务名称")
    private String name;
    @ApiModelProperty("流程定义id")
    private String processDefinitionId;
    @ApiModelProperty("流程实例id")
    private String processInstanceId;
    @ApiModelProperty("单据实例id")
    private String receiptId;
    @ApiModelProperty("流程状态: false:挂起，true：进行中")
    private Boolean state;
    @ApiModelProperty("当前任务执行人")
    private String currentAssignee;
    @ApiModelProperty("最新批注信息")
    private String lastComment;
}
