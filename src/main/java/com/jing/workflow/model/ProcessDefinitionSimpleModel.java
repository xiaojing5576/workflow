package com.jing.workflow.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: huangjingyan
 * @Date: 2021/5/7 13:44
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel
public class ProcessDefinitionSimpleModel {
    @ApiModelProperty("流程定义key")
    private String processDefinitionKey;
    @ApiModelProperty("流程定义id")
    private String processDefinitionId;
    @ApiModelProperty("流程名称")
    private String name;
    @ApiModelProperty("流程说明信息")
    private String des;
    @ApiModelProperty("分组key")
    private String groupKey;
    @ApiModelProperty("分组名称")
    private String groupName;
    @ApiModelProperty("单据类型")
    private String receiptCode;
    @ApiModelProperty("状态（1：创建，2：启用，3：挂起，4：禁用）")
    private Integer status;
    @ApiModelProperty("创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    @ApiModelProperty("更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;
}
