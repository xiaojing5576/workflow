package com.jing.workflow.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jing.workflow.config.LongSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 10:00
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel("流程执行人配置")
@TableName("hec_operator_conf")
public class WorkflowOperatorConf {

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

    @ApiModelProperty("流程定义版本号")
    @TableField("PROCESS_DEFINITION_VERSION")
    private String processDefinitionVersion;

    @ApiModelProperty("流程节点id")
    @TableField("PROCESS_DEFINITION_TASK_ID")
    private String processDefinitionTaskId;

    @ApiModelProperty("执行人参数key")
    @TableField("OPERATOR_PARAM_KEY")
    private String operatorParamKey;

    @ApiModelProperty("执行人集合参数key")
    @TableField("OPERATOR_COLLECTION_PARAM_KEY")
    private String operatorCollectionParamKey;

    @ApiModelProperty("执行人来源:" +
            "指定成员：SPECIFIC" +
            "自选（单个）：OPTIONAL" +
            "自选（多个）：MULTI_OPTIONAL" +
            "部门主管：DEPARTMENT_MANAGER" +
            "直接主管：DIRECT_MANAGER" +
            "组织架构角色：ORG_ROLE"+
            "自定义角色：SYS_ROLE")
    @TableField("OPERATOR_SOURCE")
    private String operatorSource;

    @ApiModelProperty("执行人为空时执行策略：直接通过：PASS，转交管理员：TO_MANAGER")
    @TableField("OPERATOR_NULLABLE_STRATEGY")
    private String operatorNullableStrategy;

    @ApiModelProperty("多人时审批方式：会签：JOINTLY_SIGN，或签：OR_SIGN")
    @TableField("PERMIT_TYPE")
    private String permitType;

    @ApiModelProperty("候选执行人")
    @TableField("CANDIDATE_OPERATOR")
    private String candidateOperator;

    @ApiModelProperty("架构节点候选值")
    @TableField("CANDIDATE_ORG_NODE_CODE")
    private String candidateOrgNodeCode;

    @ApiModelProperty("候选角色")
    @TableField("CANDIDATE_ROLE_CODE")
    private String candidateRoleCode;

    @ApiModelProperty("自选用户组织架构（前端渲染用）")
    @TableField("RENDER_CANDIDATE_OPERATOR")
    private String renderCandidateOperator;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    @TableField("UPDATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;
}
