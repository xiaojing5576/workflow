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
 * @Date: 2021/4/27 9:29
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel("审批流基本信息")
@TableName("hec_definition_baseinfo")
public class WorkflowDefinitionBaseInfo {
    @TableId("ID")
    @JsonSerialize(using = LongSerializer.class)
    private Long id;
    @ApiModelProperty("流程定义key")
    @TableField("PROCESS_DEFINITION_KEY")
    private String processDefinitionKey;
    @ApiModelProperty("流程基本信息名称")
    @TableField("NAME")
    private String name;
    @ApiModelProperty("流程说明信息")
    @TableField("DES")
    private String des;
    @ApiModelProperty("分组key")
    @TableField("GROUP_KEY")
    private String groupKey;
    @ApiModelProperty("管理员人员工号")
    @TableField("ADMIN")
    private String admin;
    @ApiModelProperty("状态（1：创建，2：启用，3：挂起，4：禁用）")
    @TableField("STATUS")
    private Integer status;
    @ApiModelProperty("最新版本号")
    @TableField("PROCESS_DEFINITION_VERSION")
    private String processDefinitionVersion;

    @ApiModelProperty("最后更新人工号")
    @TableField("UPDATE_BY")
    private String updateBy;

    @ApiModelProperty("单据类型")
    @TableField("RECEIPT_CODE")
    private String receiptCode;

    @ApiModelProperty("适用组织架构编码")
    @TableField("FIT_ORG_CODES")
    private String fitOrgCodes;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    @ApiModelProperty("更新时间")
    @TableField("UPDATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;
}
