package com.jing.workflow.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/5/7 17:14
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@ApiModel
@Data
public class ReceiptProcessInstanceDetail {
    @ApiModelProperty("单据Id")
    private String receiptId;

    @ApiModelProperty("流程实例ID")
    private String processInstanceId;

    @ApiModelProperty("流程实例状态")
    private Integer processInstanceStatus;

    @ApiModelProperty("当前执行人")
    private List<String> currentExeUser;

    @ApiModelProperty("最后执行人")
    private String lastExeUser;

    @ApiModelProperty("审批时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp auditTime;
}
