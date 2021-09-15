package com.jing.workflow.model;

import lombok.Data;

/**
 * @Author: huangjingyan
 * @Date: 2021/5/21 14:12
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Data
public class UserResponseBody {

    private String code;
    private String msg;
    private Object data;
}
