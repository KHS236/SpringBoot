package com.example.demo.config.auth.JWT;


import com.example.demo.domain.entity.JwtToken;
import com.example.demo.domain.repository.JwtTokenRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Autowired
    JWTTokenProvider jwtTokenProvider;
    @Autowired
    JwtTokenRepository jwtTokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //전
        //access-token 쿠키 받아 Authentication 생성후 SecurityContextHolder에 저장
        System.out.println("[JWTAuthorizationFilter] doFilterInternal....");

        //액세스토큰추출
        String token=null;
        //쿠키가 단 하나도 존재하지 않을 경우 접속 자체가 안 되기 때문에 cookies도 하나 만들어둠
        Cookie[] cookies = request.getCookies();
        if(cookies!=null)
        {
            token =
                    Arrays.stream(request.getCookies())
                            .filter((cookie)->{return cookie.getName().equals(JWTProperties.ACCESS_TOKEN_COOKIE_NAME);})
                            .findFirst()
                            .map ((cookie)->{return cookie.getValue();})
                            .orElse(null);
        }


        System.out.println("Token : " + token);
        if(token!=null){
            //access-token 쿠키 받아 authentication 생성후 SecurityContextHolder에 저장
            //1)access-token 만료인지 확인
            try{
                if(jwtTokenProvider.validateToken(token)){
                    //1-1)access-token==만료 o ? refresh-token 만료 여부확인
                    Authentication authentication = jwtTokenProvider.getAuthentication(token);
                    if(authentication!=null){
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }

                }
            }catch(ExpiredJwtException e){
                //1-2)access-token==만료 x ? Authentication 생성
                System.out.println("ExpiredJwtException ,,,,, AccessToken Expired" + e.getMessage());
                //2) RefreshToken의 만료유무
                JwtToken entity = jwtTokenRepository.findByAccessToken(token);
                if(entity!=null){

                    try {
                        if(jwtTokenProvider.validateToken(entity.getRefreshToken()  )) {
                        //2-1) RefreshToken == 만료 ? o -> DB's Token Info 삭제
                        //Accesstoken 재발급
                        long now = (new Date()).getTime();//현재시간
                        String accessToken = Jwts.builder()
                                .setSubject(entity.getUsername()) //Payload
                                .setExpiration(new Date(now + JWTProperties.ACCESS_TOKEN_EXPIRATION_TIME))    //만료날짜
                                .signWith(jwtTokenProvider.getkey(), SignatureAlgorithm.HS256)    //서명값
                                .claim("username",entity.getUsername())   //본문 내용
                                .claim("auth",entity.getAuth())   //본문 내용"ROLE_USER,ROLE_ADMIN"
                                .compact();
                            //쿠키로전달
                            Cookie cookie = new Cookie(JWTProperties.ACCESS_TOKEN_COOKIE_NAME,accessToken);
                            cookie.setMaxAge(JWTProperties.ACCESS_TOKEN_EXPIRATION_TIME);//accesstoken 유지시간
                            cookie.setPath("/");        //쿠키 적용경로(/ : 모든경로)
                            response.addCookie(cookie); //응답정보에 쿠키 포함
                            //DB's AccessToken 값 갱신
                            entity.setAccessToken(accessToken);
                            jwtTokenRepository.save(entity);
                        }
                    }catch (ExpiredJwtException e2){
                        // 2-2 RefreshToken == 만료 ? x -> AccessToken 재발급 -> 쿠키전달 + DB Token Info 갱신
                        System.out.println("ExpiredJwtException ...RefreshToken Expired..." + e2.getMessage());
                    }catch (Exception e2){

                    }




                }
            }catch (Exception e){

            }



            //2)


        }else {
//            response.sendRedirect("/login");
//            return;
        }
        filterChain.doFilter(request,response);

        //후


    }
}
