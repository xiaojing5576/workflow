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
 * @Date: 2021/5/12 14:01
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
public class ProcessBaseInfoDetailModel {
    @JsonSerialize(using = LongSerializer.class)
    private Long id;
    @ApiModelProperty("流程定义key")
    private String processDefinitionKey;
    @ApiModelProperty("流程基本信息名称")
    private String name;
    @ApiModelProperty("流程说明信息")
    private String des;
    @ApiModelProperty("分组key")
    private String groupKey;
    @ApiModelProperty("管理员人员工号")
    private String admin;
    @ApiModelProperty("状态（1：创建，2：启用，3：挂起，4：禁用）")
    private Integer status;
    @ApiModelProperty("最新版本号")
    private String processDefinitionVersion;

    @ApiModelProperty("最后更新人工号")
    private String updateBy;

    @ApiModelProperty("单据类型")
    private String receiptCode;

    @ApiModelProperty("适用组织架构编码")
    private List<String> fitOrgCodes;

    @ApiModelProperty("创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    @ApiModelProperty("更新时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;
}
