package com.lgcns.icst.springthymeleaf.lec4.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        UUID uuid = UUID.randomUUID();
        log.info("### INTERCEPTOR[{}] : [{}] {}", uuid, method, requestURI);
        log.info("### INTERCEPTOR[{}] : params = {}", uuid, paramMap(request.getParameterMap()));

        return true;
    }

    private Map<String, String> paramMap(Map<String, String[]> parameterMap) {
        Map<String, String> paramMap = new HashMap<>();
        for (String key : parameterMap.keySet()) {
            paramMap.put(key, parameterMap.get(key)[0]);    // 동일 key 에 대한 단일 파라미터만 사용한다.
        }
        return paramMap;
    }
}
