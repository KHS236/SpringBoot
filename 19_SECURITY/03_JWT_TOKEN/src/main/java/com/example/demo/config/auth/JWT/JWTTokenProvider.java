package com.example.demo.config.auth.JWT;


import com.example.demo.config.auth.PrincipalDetails;
import com.example.demo.domain.dtos.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JWTTokenProvider {

    @Autowired
    private UserRepository userRepository;

    //Key
    private Key key;

    public Key getkey(){
        return key;
    }


    //생성자가 생성된 이후에 동작하는 기본함수 지정 어노테이션
    //기본 생성자가 생성된 이후 처리해야할 초기값을 넣을 때 사용
    @PostConstruct
    public void init(){
        byte[] keyBytes = KeyGenerator.keyGen();
        //hmac = 해시화알고리즘 / Sha = 암호화 알고리즘 (대충 이런 느낌)
        this.key = Keys.hmacShaKeyFor(keyBytes);

    }

    public TokenInfo generateToken(Authentication authentication){

        //계정정보 - 계정명 / auth(role)
        String authorities = authentication  .getAuthorities()
                    // -> Collection<SimpleGrantedAuthority> aurhorities 반환
                        .stream()
                    // -> Stream함수 사용 예정
                        .map((role)->{return role.getAuthority();})
                        // -> 각각 GrantedAuthority("ROLE~")들을 문자열 값으로 반환해서 map처리
                        .collect(Collectors.joining(","));
                // -> 각각 role(ROLE_ADMIN ROLE_USER...)를 기준 다시 문자열로 묶음 ("ROLE_USER,ROLE_ADMIN")


        //AccessToken(서버의 서비스 이용제한 정책)
        long now = (new Date()).getTime();//현재시간
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName()) //Payload
                .setExpiration(new Date(now + JWTProperties.ACCESS_TOKEN_EXPIRATION_TIME))    //만료날짜
                .signWith(key,SignatureAlgorithm.HS256)    //서명값
                .claim("username",authentication.getName())   //본문 내용
                .claim("auth",authorities)   //본문 내용
                .compact();

        //RefreshToken (AccessToken 만료시 갱신 처리)
        String refreshToken = Jwts.builder()
                .setSubject("Refresh_Token_Title")         //Payload
                .setExpiration(new Date(now + JWTProperties.REFRESH_TOKEN_EXPIRATION_TIME))    //만료날짜
                .signWith(key,SignatureAlgorithm.HS256)    //서명값
                .compact();

        //TokenInfo
        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }

    public Authentication getAuthentication(String accessToken) throws ExpiredJwtException{

        Claims claims =
        Jwts.parser().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();

        String username = claims.getSubject();
        username =(String) claims.get("username"); //위아래 똑같음 username꺼내는작업
        String auth = (String)claims.get("auth");//"ROLE_USER,ROLE_ADMIN"

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        String roles [] = auth.split(","); //["ROLE_ADMIN","ROLE_USER"]
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }

        PrincipalDetails principalDetails = null;
        UserDto dto = null;
        if(userRepository.existsById(username)){

            dto = new UserDto();
            dto.setUsername(username);
            dto.setRole(auth);
            dto.setPassword(null);

            principalDetails = new PrincipalDetails(dto);
        }

        if(principalDetails!=null){
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(principalDetails,null,authorities);
                    return authenticationToken;
        }

        return null;

    }

    //토큰이 만료되었는지 체크
    public boolean validateToken(String token) throws JwtException {

        boolean isValid = false;
        try {
            Jwts.parser().setSigningKey(key).build().parseClaimsJws(token);
            isValid =  true;
        }catch (ExpiredJwtException e){//JWT토큰이 만료됐을 때 예외처리
            log.info("[ExpiredJwtException],,,,," + e.getMessage());
//            throw new ExpiredJwtException(header,claims정보,message);
            throw new ExpiredJwtException(null,null,null);
        }
        return isValid;
    }


}
