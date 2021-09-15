package com.jing.workflow.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jing.workflow.common.response.ResponseResult;
import com.jing.workflow.common.response.ResultCode;
import com.jing.workflow.model.TaskCompleteModel;
import com.jing.workflow.model.TaskDetailVO;
import com.jing.workflow.model.TaskVO;
import com.jing.workflow.service.ProcessTaskService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;


/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 14:10
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@RequestMapping("/task")
@RestController
public class TaskController {
    @Resource
    private ProcessTaskService processTaskService;

    @ApiOperation("提交任务:返回流程实例id")
    @PostMapping("/complete")
    public ResponseResult<String> completeTask(@RequestBody TaskCompleteModel model){
        String processInstanceId = processTaskService.completeTask(model);
        return new ResponseResult<>(ResultCode.OK,processInstanceId);
    }

    @ApiOperation("查询待完成任务列表：可分页（page和size）")
    @GetMapping("/wait-todo/task/query")
    public ResponseResult<Page<TaskVO>> queryWaitDoTasks(@RequestParam String assignee, @ApiIgnore Pageable pageable){
        Page<TaskVO> page = new Page<>(pageable.getPageNumber()==0?1:pageable.getPageNumber(),pageable.getPageSize());
        page = processTaskService.getWaitTodoTaskList(assignee,page);
        return new ResponseResult<>(ResultCode.OK,page);
    }

    @ApiOperation("查询已完成任务列表：可分页（page和size）")
    @GetMapping("/done/task/query")
    public ResponseResult<Page<TaskVO>> queryDoneTasks(@RequestParam String assignee, @ApiIgnore Pageable pageable){
        Page<TaskVO> page = new Page<>(pageable.getPageNumber()==0?1:pageable.getPageNumber(),pageable.getPageSize());
        page = processTaskService.getDoneTaskList(assignee,page);
        return new ResponseResult<>(ResultCode.OK,page);
    }

    @ApiOperation("转交任务")
    @GetMapping("/transfer")
    public ResponseResult<String> transferTask(@RequestParam String taskId,@RequestParam String assignee){
        String result = processTaskService.transferTask(taskId,assignee);
        return new ResponseResult<>(ResultCode.OK,result);
    }

    @ApiOperation("查询任务详情")
    @GetMapping("/detail/query")
    public ResponseResult<TaskDetailVO> getTaskDetail(@RequestParam String taskId){
        TaskDetailVO detail = processTaskService.getTaskDetail(taskId);
        return new ResponseResult<>(ResultCode.OK,detail);
    }

    @ApiModelProperty("执行期间加签")
    @PostMapping("/signers/add")
    public ResponseResult<String> addSigner(String taskId, List<String> addSigners){
        String result = processTaskService.addSigner(taskId,addSigners);
        return new ResponseResult<>(ResultCode.OK,result);
    }

}
