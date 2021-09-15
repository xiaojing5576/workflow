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
 * @Date: 2021/4/27 10:35
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@ApiModel("表单参数")
@Data
@TableName("hec_param")
public class WorkflowParam {
    @TableId("ID")
    @JsonSerialize(using = LongSerializer.class)
    private Long id;

    @ApiModelProperty("参数key")
    @TableField("PARAM_KEY")
    private String paramKey;

    @ApiModelProperty("流程定义id")
    @TableField("PROCESS_DEFINITION_ID")
    private String processDefinitionId;

    @ApiModelProperty("流程定义key")
    @TableField("PROCESS_DEFINITION_KEY")
    private String processDefinitionKey;

    @ApiModelProperty("流程定义版本")
    @TableField("PROCESS_DEFINITION_VERSION")
    private String processDefinitionVersion;

    @ApiModelProperty("参数名称")
    @TableField("PARAM_NAME")
    private String paramName;

    @ApiModelProperty("提示信息")
    @TableField("TIP")
    private String tip;

    @ApiModelProperty("参数类型：text：单行文本" +
            "textArea：多行文本" +
            "amount：金额" +
            "number :数字" +
            "radio:单选框" +
            "checkbox：复选框" +
            "date：单个日期" +
            "date-group:日期区间" +
            "label:说明文字" +
            "picture：图片" +
            "attachment：附件" +
            "proof：单据")
    @TableField("PARAM_TYPE")
    private String paramType;

    @ApiModelProperty("参数候选值")
    @TableField("PARAM_CANDIDATE_VALUE")
    private String paramCandidateValue;

    @ApiModelProperty("参数序号")
    @TableField("SORT_NUMBER")
    private Integer sortNumber;

    @ApiModelProperty("是否必填：true：必填，false：选填")
    @TableField("REQUIRED")
    private Boolean required;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
}
