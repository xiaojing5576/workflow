package com.jing.workflow.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 14:48
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel("基本信息保存model")
public class ProcessBaseInfoSaveModel {
    @ApiModelProperty("流程定义key")
    private String processDefinitionKey;
    @ApiModelProperty("流程名称")
    private String name;
    @ApiModelProperty("流程说明信息")
    private String des;
    @ApiModelProperty("单据类型")
    private String receiptCode;
    @ApiModelProperty("分组key")
    private String groupKey;
    @ApiModelProperty("管理员人员工号")
    private String admin;
    @ApiModelProperty("分配的组织架构节点")
    private List<String> fitOrgCodes;
}
