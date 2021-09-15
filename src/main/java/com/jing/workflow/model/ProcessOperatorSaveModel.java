package com.jing.workflow.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 15:38
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel("流程操作人配置保存model")
public class ProcessOperatorSaveModel {

    @ApiModelProperty("流程定义id")
    private String processDefinitionId;

    @ApiModelProperty("流程定义key")
    private String processDefinitionKey;

    @ApiModelProperty("流程定义版本号")
    private String processDefinitionVersion;

    @ApiModelProperty("流程执行人配置列表")
    private List<ProcessOperatorSimpleModel> operatorConfs;
}
