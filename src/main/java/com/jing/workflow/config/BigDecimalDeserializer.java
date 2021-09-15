package com.jing.workflow.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @Author: huangjingyan
 * @Date: 2021/2/23 15:38
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
public class BigDecimalDeserializer  extends JsonDeserializer<BigDecimal> {

    @Override
    public BigDecimal deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        if (jsonParser != null && StringUtils.isNotEmpty(jsonParser.getText())) {
            return new BigDecimal(jsonParser.getText().replaceAll(",",""));
        } else {
            return BigDecimal.ZERO;
        }
    }
}
