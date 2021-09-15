package com.jing.workflow.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 15:20
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/

@Data
@ApiModel("参数配置保存model")
public class ProcessParamConfSaveModel {

    @ApiModelProperty("流程定义Id")
    private String processDefinitionId;

    @ApiModelProperty("流程定义key")
    private String processDefinitionKey;

    @ApiModelProperty("参数配置列表")
    private List<ProcessParamConfDetailModel> paramConfigs;

}
