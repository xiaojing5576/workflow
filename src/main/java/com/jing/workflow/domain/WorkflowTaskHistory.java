package com.jing.workflow.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jing.workflow.config.LongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: huangjingyan
 * @Date: 2021/5/17 16:22
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel
@TableName("hec_workflow_task_history")
public class WorkflowTaskHistory {
    @TableId("ID")
    @ApiModelProperty("记录id")
    @JsonSerialize(using = LongSerializer.class)
    private Long id;

    @TableField("PROCESS_INSTANCE_ID")
    @ApiModelProperty("流程实例ID")
    private String processInstanceId;

    @TableField("PROCESS_DEFINITION_ID")
    @ApiModelProperty("流程定义id")
    private String processDefinitionId;

    @TableField("PROCESS_DEFINITION_KEY")
    @ApiModelProperty("流程定义key")
    private String processDefinitionKey;

    @TableField("RECEIPT_ID")
    @ApiModelProperty("单据id")
    private String receiptId;

    @TableField("TASK_DEFINITION_KEY")
    @ApiModelProperty("任务节点定义key")
    private String taskDefinitionKey;

    @ApiModelProperty("任务ID")
    @TableField("TASK_ID")
    private String taskId;

    @ApiModelProperty("任务状态：0：未完成，1：完成")
    @TableField("STATUS")
    private Integer status;

    @ApiModelProperty("审批意见")
    @TableField("OPINION")
    private Integer opinion;

    @ApiModelProperty("批注")
    @TableField("COMMENTS")
    private String comments;

    @ApiModelProperty("任务开始时间")
    @TableField("START_TIME")
    private Timestamp startTime;

    @ApiModelProperty("任务结束时间")
    @TableField("END_TIME")
    private Timestamp endTime;

    @ApiModelProperty("执行人列表")
    @TableField("OPERATORS")
    private String operators;

}
