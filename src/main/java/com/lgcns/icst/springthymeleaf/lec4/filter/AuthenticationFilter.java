package com.lgcns.icst.springthymeleaf.lec4.filter;

import com.lgcns.icst.springthymeleaf.lec4.constant.SessionKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String[] whiteList = {"/lec4/member/login", "/lec4/member/sign-up", "*/css/*", "*/js/*"};
        String requestURI = httpServletRequest.getRequestURI().replace(httpServletRequest.getContextPath(), "");

        HttpSession session = httpServletRequest.getSession(false);
        if (PatternMatchUtils.simpleMatch(whiteList, requestURI)
                || (session != null && session.getAttribute(SessionKey.MEMBER_ID) != null)) {
            chain.doFilter(request, response);
        } else {
            log.warn("### FILTER: 로그인 되지 않은 사용자의 요청입니다.");
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/lec4/member/login?redirectURI=" + requestURI);
        }
    }
}
