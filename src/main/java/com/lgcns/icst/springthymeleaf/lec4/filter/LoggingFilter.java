package com.lgcns.icst.springthymeleaf.lec4.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class LoggingFilter implements Filter {

    public String[] blackList = {"/*/css/*", "/*/js/*", "/*/*.ico"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();

        if (!PatternMatchUtils.simpleMatch(blackList, requestURI)) {
            String method = httpServletRequest.getMethod();
            log.info("### FILTER: [{}] {}", method, requestURI); // [GET] /lec4/member/login
        }

        chain.doFilter(request, response);
    }
}
