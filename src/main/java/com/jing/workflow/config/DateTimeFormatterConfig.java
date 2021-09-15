package com.jing.workflow.config;

import com.jing.workflow.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @Author: huangjingyan
 * @Date: 2020/12/22 14:21
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Configuration
public class DateTimeFormatterConfig {

    @Bean
    public Formatter<LocalDate> localDateFormatter(){
        return new Formatter<LocalDate>() {
            @Override
            public LocalDate parse(String s, Locale locale) throws ParseException {
                if(StringUtils.isBlank(s)){
                    return null;
                }
                return LocalDate.parse(s, DateTimeFormatter.ofPattern(Constants.DEFAULT_LOCAL_DATE_PATTERN,locale));
            }

            @Override
            public String print(LocalDate localDate, Locale locale) {
                return localDate.format(DateTimeFormatter.ofPattern(Constants.DEFAULT_LOCAL_DATE_PATTERN,locale));
            }
        };
    }

}
