package com.example.demo.controller;

import com.example.demo.domain.dtos.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/login")
    public void login(){
        log.info("GET /login......");
    }

    @GetMapping("/user")
    public void user(){
        log.info("GET /user......");
    }

    @GetMapping("/manager")
    public void manager(){
        log.info("GET /manager......");
    }

    @GetMapping("/admin")
    public void admin(){
        log.info("GET /admin......");
    }

    @GetMapping("/join")
    public void join(){
        log.info("GET /join......");
    }
    
    //시큐리티컨피그에서 만들었던 패스워드 암호화 빈 연결
    @Autowired
    private PasswordEncoder passwordencoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/join")
    public String join_post(UserDto dto){
        log.info("POST /join_post......"+dto);
        String pwd = passwordencoder.encode(dto.getPassword()); //암호화작업
        //dto -> entity
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(pwd);
        user.setRole("ROLE_USER");
        userRepository.save(user);
        boolean isJoin = true;
        if(isJoin){
            return "redirect:/login";
        }
        return "join";

    }
}
