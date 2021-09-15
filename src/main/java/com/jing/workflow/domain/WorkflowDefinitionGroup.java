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
 * @Date: 2021/4/27 9:55
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel
@TableName("hec_definition_group")
public class WorkflowDefinitionGroup {

    @TableId("ID")
    @JsonSerialize(using = LongSerializer.class)
    private Long id;

    @ApiModelProperty("分组key值")
    @TableField("GROUP_KEY")
    private String groupKey;

    @ApiModelProperty("分组名称")
    @TableField("GROUP_NAME")
    private String groupName;

    @ApiModelProperty("状态（1：启用，2：禁用）")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;

    @ApiModelProperty("更新时间")
    @TableField("UPDATE_TIME")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp updateTime;

}
