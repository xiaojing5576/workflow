package com.jing.workflow.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * @Author: huangjingyan
 * @Date: 2021/1/18 13:10
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Configuration
public class BigDecimalFormatterConfig {

    @Bean
    public Formatter<BigDecimal> bigDecimalFormatter(){
        return new Formatter<BigDecimal>(){
            @Override
            public BigDecimal parse(String s, Locale locale) throws ParseException {
                if(StringUtils.isBlank(s)){
                    return null;
                }
                return new BigDecimal(s.replaceAll(",",""));
            }

            @Override
            public String print(BigDecimal bigDecimal, Locale locale) {
                DecimalFormat df1 = new DecimalFormat("#,##0.00");
                String str = df1.format(bigDecimal.setScale(2, RoundingMode.HALF_UP));
                return str;
            }
        };
    }
}
