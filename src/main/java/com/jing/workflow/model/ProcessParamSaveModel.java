package com.jing.workflow.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 14:58
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@ApiModel("流程参数保存model")
@Data
public class ProcessParamSaveModel {

    @ApiModelProperty("流程定义Id")
    private String processDefinitionId;

    @ApiModelProperty("流程定义key")
    private String processDefinitionKey;

    @ApiModelProperty("流程参数定义列表")
    private List<ProcessParamDetailModel> params;



}
