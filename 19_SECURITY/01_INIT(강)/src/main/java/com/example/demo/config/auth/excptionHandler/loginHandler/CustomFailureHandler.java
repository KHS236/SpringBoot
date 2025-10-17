package com.example.demo.config.auth.excptionHandler.loginHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
public class CustomFailureHandler implements AuthenticationFailureHandler {

    //없는 계정이거나 패스워드가 불일치 한 로그인 시도시 경우 동작
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.error("CustomFailureHandler's onAuthenticationFailure invoke....!");
        response.sendRedirect("/login?error="+URLEncoder.encode(exception.getMessage(),"utf-8"));
//        URLEncoder.encode(exception.getMessage()"m","utf-8");



    }
}
