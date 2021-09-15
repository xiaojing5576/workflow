package com.jing.workflow.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jing.workflow.config.LongSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: huangjingyan
 * @Date: 2021/5/7 17:04
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel
@TableName("hec_workflow_receipt_instance")
public class WorkflowReceiptInstance {
    @ApiModelProperty("记录id")
    @JsonSerialize(using = LongSerializer.class)
    @TableId("ID")
    private Long id;

    @ApiModelProperty("流程实例ID")
    @TableField("PROCESS_INSTANCE_ID")
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

    @TableField("RECEIPT_CODE")
    @ApiModelProperty("单据类型")
    private String receiptCode;

    @ApiModelProperty("发起人")
    @TableField("APPLYER")
    private String applyer;

    @TableField("CREATE_TIME")
    @ApiModelProperty("创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @TableField("UPDATE_TIME")
    @ApiModelProperty("更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;

}
