package com.jing.workflow.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: huangjingyan
 * @Date: 2021/5/8 9:39
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@ApiModel
@Data
public class ProcessConditionSaveModel {
    @ApiModelProperty("流程定义key")
    private String processDefinitionKey;
    @ApiModelProperty("流程定义id")
    private String processDefinitionId;
    @ApiModelProperty("流程条件json字符串")
    private String condition;
}
