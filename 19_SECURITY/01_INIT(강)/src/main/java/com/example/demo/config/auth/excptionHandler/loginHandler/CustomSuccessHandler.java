package com.example.demo.config.auth.excptionHandler.loginHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.List;

@Slf4j
public class CustomSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("CustomSuccessHandler's onAuthenticationSuccess invoke....!");
        //기본
        response.sendRedirect("/");


        String redirectUrl = "/";

        //Role 별로 redirect 경로 수정
        for(GrantedAuthority authority : authentication.getAuthorities()){
            log.info("authority : " + authority);
            String role = authority.getAuthority();

            if(role.contains("ROLE_ADMIN")){
                // /admin 리다이렉트
                redirectUrl = "/admin";
                break;
            }else if(role.contains("ROLE_USER")){
                // /manager 리다이렉트
                redirectUrl = "/manager";
                break;
            }else {
                // /user 리다이렉트
                redirectUrl = "/user";
                break;
            }
        }
            response.sendRedirect(redirectUrl);

    }
}
