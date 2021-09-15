package com.jing.workflow.common.exception;


import com.jing.workflow.common.response.ResultCode;

public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 6279326621734989269L;

    public int errorCode;

    public String errorMsg;

    private ResultCode code;

    public ServiceException(String message) {
        super(message);
        this.errorMsg = message;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.errorMsg = message;
    }

    public ServiceException(int errorCode, String message) {
        this(errorCode + "#:" + message);
        this.errorCode = errorCode;
        this.errorMsg = message;
    }

    public ServiceException(int errorCode, String message, Throwable cause) {
        this(errorCode + "#:" + message, cause);
    }

    public ServiceException(ResultCode code) {
        this(code.getCode() + "#:" + code.getMsg());
        this.code = code;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ResultCode getCode() {
        return this.code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }
}
