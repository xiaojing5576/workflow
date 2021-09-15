package com.jing.workflow.common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @Author: huangjingyan
 * @Date: 2021/4/27 11:08
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
public class Constants {

    public static enum PROCESS_OPERATOR_SOURCE{

        SPECIFIC("SPECIFIC","指定成员"),
        ORG_ROLE("ORG_ROLE","组织架构角色"),
        BASED_APPLYER_ROLE("BASED_APPLYER_ROLE","基于发起人的角色")
        ;

        PROCESS_OPERATOR_SOURCE(String code, String msg) {
            this.msg = msg;
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        private String msg;
        private String code;

    }


    public static enum PROCESS_DEFINITION_STATUS{
        UN_START(1,"创建"),
        STARTING(2,"启用中"),
        END(3,"挂起"),
        TERMINAL(4,"禁用")
        ;

        PROCESS_DEFINITION_STATUS(Integer code, String msg) {
            this.msg = msg;
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        private String msg;
        private Integer code;

    }
    public static enum PROCESS_INSTANCE_STATUS{
        AUDITING(1,"审批中"),
        SUSPEND(2,"挂起"),
        END(3,"结束")
        ;

        PROCESS_INSTANCE_STATUS(Integer code, String msg) {
            this.msg = msg;
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        private String msg;
        private Integer code;

    }

    public static enum IS_PROCESS_APPLIER{
        YES(1,"是"),
        NO(0,"不是")
        ;

        IS_PROCESS_APPLIER(Integer code, String msg) {
            this.msg = msg;
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        private String msg;
        private Integer code;

    }

    public static enum PERMIT_TYPE{
        OR_SIGN("OR_SIGN","或签"),
        JOINTLY_SIGN("JOINTLY_SIGN","会签")
        ;

        PERMIT_TYPE(String code, String msg) {
            this.msg = msg;
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        private String msg;
        private String code;
    }

    public static enum OPERATOR_NULLABLE_STRATEGY{
        PASS("PASS","直接通过"),
        TO_MANAGER("TO_MANAGER","转交管理员")
        ;

        OPERATOR_NULLABLE_STRATEGY(String code, String msg) {
            this.msg = msg;
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        private String msg;
        private String code;
    }

    public static enum TASK_STATUS{
        COMPLETE(1,"完成"),
        UN_COMPLETE(0,"未完成")
        ;

        TASK_STATUS(Integer code, String msg) {
            this.msg = msg;
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        private String msg;
        private Integer code;
    }

    public static enum TASK_OPINION{
        UN_DO(0,"未审批"),
        AGREE(1,"同意"),
        REFUSE(2,"拒绝")
        ;

        TASK_OPINION(Integer code, String msg) {
            this.msg = msg;
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        private String msg;
        private Integer code;
    }

    /**
     * 默认前端传入参数解析类型
     */
    public static final String DEFAULT_LOCAL_DATE_PATTERN="yyyy-MM-dd";

    /**
     * 默认前端浮点型显示规则
     */
    public static String DECIMAL_FORMAT_PATTERN="#,##0.00";

    public static DecimalFormat DECIMAL_FORMAT=new DecimalFormat(DECIMAL_FORMAT_PATTERN);

    public static BigDecimal decimalFormat(String value){
        value=value.replace("%","");
        Float f = Float.valueOf(value) / 100;
        return new BigDecimal(f);
    }
}
