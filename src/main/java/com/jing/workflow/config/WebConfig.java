package com.jing.workflow.config;

import com.jing.workflow.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @Author: huangjingyan
 * @Date: 2021/1/20 14:14
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
//@Configuration
//@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<LocalDate>() {
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
        });

        registry.addFormatter(new Formatter<BigDecimal>(){
                @Override
                public BigDecimal parse(String s, Locale locale) throws ParseException {
                    if(StringUtils.isBlank(s)){
                        return null;
                    }
                    return new BigDecimal(s);
                }

                @Override
                public String print(BigDecimal bigDecimal, Locale locale) {
                    DecimalFormat df1 = new DecimalFormat("#.00");
                    String str = df1.format(bigDecimal.setScale(2, RoundingMode.HALF_UP));
                    return str;
                }
            });
    }
}