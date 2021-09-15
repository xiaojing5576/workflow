package com.jing.workflow.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2021/5/14 16:34
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Component
public class HttpConfig {
    @Bean
    public RestTemplate restTemplate(){

        RestTemplate restTemplate= new RestTemplate();
        restTemplate.setMessageConverters(fastJsonHttpMessageConverters().getConverters());
        return restTemplate;
    }

    public HttpMessageConverters fastJsonHttpMessageConverters(){
        FastJsonHttpMessageConverter converter=new FastJsonHttpMessageConverter();
        FastJsonConfig config=new FastJsonConfig();
        List<MediaType> mediaTypes=new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.TEXT_PLAIN);
        converter.setFastJsonConfig(config);
        converter.setSupportedMediaTypes(mediaTypes);
        converter.setDefaultCharset(StandardCharsets.UTF_8);
        return new HttpMessageConverters(converter);
    }
}
