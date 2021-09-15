package com.jing.workflow.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.jing.workflow.util.DateHelper;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @Author: huangjingyan
 * @Date: 2021/1/9 16:50
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Component
public class MybatisPlusCRAutoFillConfig implements MetaObjectHandler {
    //使用mp实现添加操作,这个方法会执行,metaObject元数据(表中的名字,表中的字段)
    @Override
    public void insertFill(MetaObject metaObject) {
        //根据名称设置属性值
        this.setFieldValByName("createTime", DateHelper.getSupposedTimeStamp(LocalDate.now()),metaObject);
        this.setFieldValByName("updateTime", DateHelper.getSupposedTimeStamp(LocalDate.now()),metaObject);
    }
    //使用mp实现修改操作,这个方法会执行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",DateHelper.getSupposedTimeStamp(LocalDate.now()),metaObject);
    }
}