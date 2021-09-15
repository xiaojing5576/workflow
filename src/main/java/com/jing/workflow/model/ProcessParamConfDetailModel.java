package com.jing.workflow.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 15:24
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel("参数配置详情")
public class ProcessParamConfDetailModel {

    @ApiModelProperty("流程节点id")
    private String processTaskId;

    @ApiModelProperty("参数key")
    private String paramKey;

    @ApiModelProperty("编辑权限：EDIT,READ_ONLY,HIDE")
    private String rightType;

}
