package com.jing.workflow.interceptor;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: huangjingyan
 * @Date: 2020/12/8 13:19
 * @Mail: citrine5576@163.com
 * @Description: TODO
 * @Version: 1.0
 **/
@Component
public class TokenInterceptor extends HandlerInterceptorAdapter implements Ordered {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        NotNeedToken annotation;
//        if (handler instanceof HandlerMethod) {
//            annotation = (NotNeedToken) ((HandlerMethod) handler).getMethodAnnotation(NotNeedToken.class);
//        } else {
//            return true;
//        }
//        if (null != annotation)
//            return true;
//        String userId = (String) SecurityUtils.getSubject().getSession().getAttribute("userId");
//        Integer roleId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("roleId");
//        request.setAttribute("userId", userId);
//        request.setAttribute("roleId", roleId);
        return true;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
