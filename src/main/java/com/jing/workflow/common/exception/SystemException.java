package com.jing.workflow.common.exception;

public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 6279326621734989269L;

    public String errorMsg;

    public SystemException(String message) {
        super(message);
        this.errorMsg = message;
    }

    public SystemException(String message, Throwable cause) {
        super(message, cause);
        this.errorMsg = message;
    }

    public String getErrorMsg() {
        return this.errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
