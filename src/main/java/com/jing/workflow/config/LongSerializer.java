package com.jing.workflow.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Author: huangjingyan
 * @Date: 2021/1/23 13:59
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
public class LongSerializer extends JsonSerializer<Long> {
    @Override
    public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(value == null){
            jsonGenerator.writeString("");
            return;
        }
        jsonGenerator.writeString(String.valueOf(value));
    }
}
