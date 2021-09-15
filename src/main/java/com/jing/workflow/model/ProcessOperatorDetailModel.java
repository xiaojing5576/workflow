package com.jing.workflow.model;

import com.jing.workflow.config.LongSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/5/19 17:30
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
public class ProcessOperatorDetailModel {
    @ApiModelProperty("记录id")
    @JsonSerialize(using = LongSerializer.class)
    private Long id;

    @ApiModelProperty("流程定义id")
    private String processDefinitionId;

    @ApiModelProperty("流程定义key")
    private String processDefinitionKey;

    @ApiModelProperty("流程定义版本号")
    private String processDefinitionVersion;

    @ApiModelProperty("流程节点id")
    private String processDefinitionTaskId;

    @ApiModelProperty("执行人参数key")
    private String operatorParamKey;

    @ApiModelProperty("执行人集合参数key")
    private String operatorCollectionParamKey;

    @ApiModelProperty("执行人来源:" +
            "指定成员：SPECIFIC" +
            "自选（单个）：OPTIONAL" +
            "自选（多个）：MULTI_OPTIONAL" +
            "部门主管：DEPARTMENT_MANAGER" +
            "直接主管：DIRECT_MANAGER" +
            "组织架构角色：ORG_ROLE"+
            "自定义角色：SYS_ROLE")
    private String operatorSource;

    @ApiModelProperty("执行人为空时执行策略：直接通过：PASS，转交管理员：TO_MANAGER")
    private String operatorNullableStrategy;

    @ApiModelProperty("多人时审批方式：会签：JOINTLY_SIGN，或签：OR_SIGN")
    private String permitType;

    @ApiModelProperty("候选执行人")
    private List<String> candidateOperator;

    @ApiModelProperty("架构节点候选值")
    private List<String> candidateOrgNodeCode;

    @ApiModelProperty("候选角色")
    private List<String> candidateRoleCode;

    @ApiModelProperty("自选用户组织架构（前端渲染用）")
    private List<String> renderCandidateOperator;

    @ApiModelProperty("创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;
}
