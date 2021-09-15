package com.jing.workflow.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jing.workflow.config.LongSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: huangjingyan
 * @Date: 2021/5/8 9:29
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@TableName("hec_definition_condition")
public class WorkflowDefinitionCondition {
    @ApiModelProperty("记录id")
    @TableId("ID")
    @JsonSerialize(using = LongSerializer.class)
    private Long id;

    @ApiModelProperty("流程定义key")
    @TableField("PROCESS_DEFINITION_KEY")
    private String processDefinitionKey;

    @ApiModelProperty("流程定义id")
    @TableField("PROCESS_DEFINITION_ID")
    private String processDefinitionId;

    @ApiModelProperty("流程版本号")
    @TableField("PROCESS_DEFINITION_VERSION")
    private String processDefinitionVersion;

    @ApiModelProperty("条件json字符串")
    @TableField("CONDITION")
    private String condition;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    @TableField("UPDATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;

}
