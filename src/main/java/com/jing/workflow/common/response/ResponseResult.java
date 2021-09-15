package com.jing.workflow.common.response;


import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class ResponseResult<T> implements Serializable {
    private static final long serialVersionUID = 3405235046557998830L;

    public static final String SUCCESS = "0";

    public static final String FAILRUE = "1";

    public static final String SELECTION = "2";

    public static final String EXCEPTION = "3";

    private String code;

    private String msg;

    private T data;

    public ResponseResult(ResultCode resultCode) {
        this(resultCode.getCode().intValue(), resultCode.getMsg(), null);
    }

    public ResponseResult(ResultCode resultCode, T data) {
        this(resultCode.getCode().intValue(), resultCode.getMsg(), data);
    }


    public ResponseResult(int code, String msg) {
        this(code, msg, null);
    }

    public ResponseResult(int code, String msg, T data) {
        this.code = String.valueOf(code);
        this.msg = msg;
        this.data = data;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = String.valueOf(code);
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String cmg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Set<Map.Entry<String, Object>> entrySet() {
        return entrySet();
    }

}
