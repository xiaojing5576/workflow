package com.jing.workflow.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/28 15:28
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
@ApiModel
public class TaskDetailVO {

    @ApiModelProperty("任务id")
    private String taskId;
    @ApiModelProperty("任务key")
    private String taskKey;
    @ApiModelProperty("任务名称")
    private String name;
    @ApiModelProperty("流程定义id")
    private String processDefinitionId;
    @ApiModelProperty("流程实例id")
    private String processInstanceId;
    @ApiModelProperty("流程状态: false:挂起，true：进行中")
    private Boolean state;
    @ApiModelProperty("当前任务执行人")
    private String currentAssignee;
    @ApiModelProperty("基础表单参数定义")
    private List<ProcessParamDetailModel> paramDefinitions;
    @ApiModelProperty("基础表单参数权限配置")
    private List<ProcessParamConfDetailModel> paramConfigs;
    @ApiModelProperty("基础表单参数值")
    private Map<String,Object> paramValues;
    @ApiModelProperty("祖辈任务节点")
    private List<TaskSimpleModel> ancestorTasks;

}
