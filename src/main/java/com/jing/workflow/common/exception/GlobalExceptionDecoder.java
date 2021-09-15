package com.jing.workflow.common.exception;

import com.jing.workflow.common.response.ResponseResult;
import com.jing.workflow.common.response.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;

/**
 * @Author: huangjingyan
 * @Date: 2020/12/8 13:24
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Slf4j
@ControllerAdvice
public class GlobalExceptionDecoder {

    @ExceptionHandler({ServiceException.class})
    @ResponseBody
    public <T> ResponseResult<T> exceptionHandler(ServiceException e, HttpServletResponse response) {
        ResponseResult<T> result = new ResponseResult(e.getCode());
        return result;
    }

    @ExceptionHandler({SystemException.class})
    @ResponseBody
    public <T> ResponseResult<T> exceptionHandler(SystemException e, HttpServletResponse response) {
        log.error("SystemException：", (Throwable) e);
        ResponseResult<T> result = new ResponseResult(ResultCode.ERROR);
        return result;
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseBody
    public <T> ResponseResult<T> exceptionHandler(ValidationException e, HttpServletResponse response) {
        ResponseResult<T> result = null;
        if (null != e.getCause()) {
            ServiceException ex = (ServiceException) e.getCause();
            result = new ResponseResult(ex.getCode());
        } else {
            result = new ResponseResult(ResultCode.PARAMETER_NOT_EMPTY);
        }
        return result;
    }
//
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public <T> ResponseResult<T> exceptionHandler(MethodArgumentNotValidException e, HttpServletResponse response) {
        ResponseResult<T> result = null;
        if (null != e.getCause()) {
            ServiceException ex = (ServiceException) e.getCause();
            result = new ResponseResult(ex.getCode());
        } else {
            result = new ResponseResult(ResultCode.PARAMETER_NOT_EMPTY);
        }
        return result;
    }

//    @ExceptionHandler({UnauthorizedException.class})
//    @ResponseBody
//    public <T> ResponseResult<T> exceptionHandler(UnauthorizedException e, HttpServletResponse response) {
//        ResponseResult<T> result = null;
//        result = new ResponseResult(ResultCode.UNAUTHORIZED);
//        return result;
//    }
//
//    @ExceptionHandler({UnknownAccountException.class})
//    @ResponseBody
//    public <T> ResponseResult<T> exceptionHandler(UnknownAccountException e, HttpServletResponse response) {
//        ResponseResult<T> result = null;
//        result = new ResponseResult(ResultCode.USER_NOTEXIST);
//        return result;
//    }
//
//    @ExceptionHandler({ExpiredCredentialsException.class})
//    @ResponseBody
//    public <T> ResponseResult<T> exceptionHandler(ExpiredCredentialsException e, HttpServletResponse response) {
//        ResponseResult<T> result = null;
//        result = new ResponseResult(ResultCode.SESSION_ERR);
//        return result;
//    }

    @ExceptionHandler({Exception.class})
    @ResponseBody
    public <T> ResponseResult<T> exceptionHandler(Exception e, HttpServletResponse response) {
        log.error("Exception：", e);
        ResponseResult<T> result = new ResponseResult(ResultCode.ERROR);
        return result;
    }

}
