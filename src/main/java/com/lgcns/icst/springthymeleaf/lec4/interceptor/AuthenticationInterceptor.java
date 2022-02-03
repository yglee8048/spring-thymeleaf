package com.lgcns.icst.springthymeleaf.lec4.interceptor;

import com.lgcns.icst.springthymeleaf.lec4.constant.SessionKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(SessionKey.MEMBER_ID) != null) {
            return true;
        } else {
            log.warn("인증 되지 않은 사용자 요청입니다.");
            String requestURI = request.getRequestURI().replace(request.getContextPath(), "");
            response.sendRedirect(request.getContextPath() + "/lec4/member/login?redirectURI=" + requestURI);
            return false;
        }
    }
}
