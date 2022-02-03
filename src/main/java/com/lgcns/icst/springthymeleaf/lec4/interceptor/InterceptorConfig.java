package com.lgcns.icst.springthymeleaf.lec4.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/js/**", "/**.ico", "/error");

        registry.addInterceptor(new AuthenticationInterceptor())
                .order(2)
                .addPathPatterns("/lec4/**")
                .excludePathPatterns("/css/**", "/js/**", "/**.ico", "/error");
    }
}
