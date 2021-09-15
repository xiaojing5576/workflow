package com.jing.workflow.config;

import com.jing.workflow.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: huangjingyan
 * @Date: 2020/12/8 11:09
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

//    @Value("${res.static.location}")
//    private String staticResourceLocation;

    @Resource
    public TokenInterceptor tokenInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/**",
                "/**/*.gif", "/**/*.jpg", "/**/*.jpeg", "/**/*.png", "/**/*.bmp", "/**/*.ico", "/**/*.css", "/**/*.js", "/**/*.txt", "/**/*.html",
                "/**/*.woff", "/**/*.ttf", "/**/*.mp3", "/**/apple-app-site-association*"});
        registry.addResourceHandler(new String[]{"swagger-ui.html"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/"});
        registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"});
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor((HandlerInterceptor) tokenInterceptor).addPathPatterns(getPatterns())
                .excludePathPatterns(new String[]{"doc.html", "/v2/**", "/swagger-resources/**", "/webjars/**", "/swagger-ui.html/**"});
    }

    private List<String> getPatterns() {
        List<String> patterns = new ArrayList<>();
        patterns.add("/tenancy/**");
        return patterns;
    }

}
