package com.jing.workflow.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jing.workflow.config.LongSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 10:47
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel
@TableName("hec_param_conf")
public class WorkflowParamConf {

    @ApiModelProperty("记录id")
    @TableId("ID")
    @JsonSerialize(using = LongSerializer.class)
    private Long id;

    @ApiModelProperty("流程定义id")
    @TableField("PROCESS_DEFINITION_ID")
    private String processDefinitionId;

    @ApiModelProperty("流程定义key")
    @TableField("PROCESS_DEFINITION_KEY")
    private String processDefinitionKey;

    @ApiModelProperty("流程定义版本")
    @TableField("PROCESS_DEFINITION_VERSION")
    private String processDefinitionVersion;

    @ApiModelProperty("参数key")
    @TableField("PARAM_KEY")
    private String paramKey;

    @ApiModelProperty("流程节点id")
    @TableField("PROCESS_DEFINITION_TASK_KEY")
    private String processTaskId;

    @ApiModelProperty("编辑权限：EDIT,READ_ONLY,HIDE")
    @TableField("RIGHT_TYPE")
    private String rightType;
}
