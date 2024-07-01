package com.project.mvc.interceptor.seongjin;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
public class BeforeLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("login") == null) {

            String redirectUri = request.getRequestURI();
            response.sendRedirect("/user/sign-in?redirect=" + redirectUri);
            return false;
        }
        return true;
    }
}
