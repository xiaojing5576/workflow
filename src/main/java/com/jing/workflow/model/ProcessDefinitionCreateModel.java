package com.jing.workflow.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 16:19
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel("流程定义发布Model")
public class ProcessDefinitionCreateModel {
    @ApiModelProperty("流程定义key")
    private String processDefinitionKey;
    @ApiModelProperty("xml内容字符串")
    private String xmlContent;
}
