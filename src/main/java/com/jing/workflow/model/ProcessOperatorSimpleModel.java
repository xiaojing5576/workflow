package com.jing.workflow.model;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 15:45
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel("执行人配置详情model")
public class ProcessOperatorSimpleModel {

    @ApiModelProperty("是否为流程发起人")
    private Boolean isApplier;

    @ApiModelProperty("流程节点id")
    private String processDefinitionTaskId;

    @ApiModelProperty("执行人参数key")
    private String operatorParamKey;

    @ApiModelProperty("执行人集合参数key")
    private String operatorCollectionParamKey;

    @ApiModelProperty("执行人来源:" +
            "指定成员：SPECIFIC" +
            "组织架构角色：ORG_ROLE"+
            "基于发起人的角色：BASED_APPLYER_ROLE")
    private String operatorSource;

    @ApiModelProperty("执行人为空时执行策略：直接通过：PASS，转交管理员：TO_MANAGER")
    private String operatorNullableStrategy;

    @ApiModelProperty("多人时审批方式：会签：JOINTLY_SIGN，或签：OR_SIGN")
    private String permitType;

    @ApiModelProperty("候选执行人")
    private List<String> candidateOperator;

    @ApiModelProperty("架构节点候选值code")
    private List<String> candidateOrgNodeCode;

    @ApiModelProperty("候选角色code")
    private List<String> candidateRoleCode;

    @ApiModelProperty("\"自选用户组织架构（前端渲染用）")
    private List<String> renderCandidateOperator;
}
