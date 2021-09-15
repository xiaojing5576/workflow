package com.jing.workflow.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 15:11
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel("参数详情model")
public class ProcessParamDetailModel {

    @ApiModelProperty("参数key")
    private String paramKey;

    @ApiModelProperty("参数名称")
    private String paramName;

    @ApiModelProperty("提示信息")
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
    private String paramType;

    @ApiModelProperty("参数候选值")
    private String paramCandidateValue;

    @ApiModelProperty("参数序号")
    private Integer sortNumber;

    @ApiModelProperty("是否必填：true：必填，false：选填")
    private Boolean required;
}
