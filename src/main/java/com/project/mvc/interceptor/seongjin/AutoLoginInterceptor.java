package com.project.mvc.interceptor.seongjin;

import com.project.mvc.entity.User;
import com.project.mvc.mapper.seongjin.UserMapper;
import com.project.mvc.service.seongjin.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class AutoLoginInterceptor implements HandlerInterceptor {

    private final UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie autoLoginCookie = WebUtils.getCookie(request, "auto");

        if (autoLoginCookie != null && request.getSession().getAttribute("login") == null) {
            String sessionId = autoLoginCookie.getValue();
            User userBySessionId = userMapper.findBySessionId(sessionId);
            log.info("세션아이디로찾은이메일: {}",userBySessionId);
            if (userBySessionId != null && LocalDateTime.now().isBefore(userBySessionId.getLimitTime())) {
                UserService.maintainLoginState(request.getSession(), userBySessionId);
            }
        }
        return true;
    }
}
