package com.jing.workflow.config;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.jing.workflow.util.DateHelper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;

/**
 * @Author: huangjingyan
 * @Date: 2020/12/22 17:41
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
public class TimeStampSerializer implements ObjectSerializer {
    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        SerializeWriter writer=serializer.out;
        Timestamp value= (Timestamp) object;
        String s= DateHelper.getDateStr(value,"yyyy-MM-dd HH:mm:ss");
        writer.write(s);
    }
}