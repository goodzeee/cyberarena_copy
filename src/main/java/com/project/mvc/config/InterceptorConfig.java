package com.project.mvc.config;

import com.project.mvc.interceptor.seongjin.AfterLoginInterceptor;
import com.project.mvc.interceptor.seongjin.BeforeLoginInterceptor;
import com.project.mvc.interceptor.seongjin.AutoLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final AutoLoginInterceptor autoLoginInterceptor;
    private final BeforeLoginInterceptor beforeLoginInterceptor;
    private final AfterLoginInterceptor afterLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoLoginInterceptor)
                .addPathPatterns("/**")
                ;
        registry.addInterceptor(beforeLoginInterceptor)
                .addPathPatterns("/user/mypage")//해당 체인에 url 추가시 로그인 이전에 할 수 없는 요청을 로그인창으로 대체해줌
                ;
        registry.addInterceptor(afterLoginInterceptor)
                .addPathPatterns("/user/sign-in", "/user/sign-up")
                ;
    }
}
