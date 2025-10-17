package com.example.demo.domain.repository;

import com.example.demo.domain.entity.JwtToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtToken,Long> {
    //access token -> 정보 삭제
    boolean deleteByAccessToken(String accessToken);
    //access token -> token정보 가져오기
    JwtToken findByAccessToken(String accessToken);
    //username -> token정보 가져오기
    JwtToken findByUsername(String username);


}
