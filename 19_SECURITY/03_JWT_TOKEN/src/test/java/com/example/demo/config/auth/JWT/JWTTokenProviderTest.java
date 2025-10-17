//package com.example.demo.config.auth.JWT;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.JwtParser;
//import io.jsonwebtoken.Jwts;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.security.Key;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class JWTTokenProviderTest {
//    @Autowired
//    private JWTTokenProvider tokenProvider;
//
//    @Test
//    public void t1() throws Exception
//    {
//        TokenInfo tokenInfo = tokenProvider.generateToken();
//        System.out.println(tokenInfo);
//        Key key = tokenProvider.getkey();
//
//        JwtParser parser = Jwts.parser()
//                                .setSigningKey(key)
//                                .build();
//                String accessToken = tokenInfo.getAccessToken();
//                Claims claims =
//                parser.parseClaimsJws(accessToken).getBody();
//
//                String username = claims.get("username").toString();
//                String role = claims.get("role").toString();
//                System.out.println("username : " + username);
//                System.out.println("role : " + role);
//    }
///*
//TokenInfo(grantType=Bearer, accessToken=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJBY2Nlc3NfVG9rZW5fVGl0bGUiLCJleHAiOjE3NjA1MTI3MTQsInVzZXJuYW1lIjoidXNlcjEiLCJyb2xlIjoiUk9MRV9VU0VSIiwibmlja25hbWUiOiJIb25nIn0.7pIYb4kMu0gUEPJvdL3w-EvRvYQ2vkyKp-2pCEhZS_c, refreshToken=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJSZWZyZXNoX1Rva2VuX1RpdGxlIiwiZXhwIjoxNzYwNTEyNzIxfQ.0ZTtQRwgRWZDT0I-XaVGBYWCr8scLlVJ6IaxRBcSB-k)
//username : user1
//role : ROLE_USER
//*/
//
//    @Test
//    public void t2() throws Exception
//    {
//
//    }
//
//
//}