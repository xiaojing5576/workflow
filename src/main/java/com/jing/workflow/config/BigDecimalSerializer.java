package com.jing.workflow.config;


import com.jing.workflow.common.Constants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * @Author: huangjingyan
 * @Date: 2020/12/22 14:25
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
public class BigDecimalSerializer  extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(BigDecimal bigDecimal, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(bigDecimal == null){
            jsonGenerator.writeString("");
            return;
        }
        jsonGenerator.writeString(Constants.DECIMAL_FORMAT.format(bigDecimal.setScale(2,RoundingMode.HALF_UP)));
    }
}
