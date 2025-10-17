package com.example.demo.config.auth.excptionHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;

@Slf4j
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    
    //인증 실패시 실행 될 함수 코드
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.error("CustomAuthenticationEntryPoint's commence invoke.....!");
        //이전에는 권한없이 admin페이지로 넘어갈 때 자동으로 로그인 페이지로 이동했지만 이제 직접 이동시켜야함

        //ROLE 별로 기본페이지로 이동
        response.sendRedirect("/login?error="+authException.getMessage());


    }
}
