package com.example.demo.controller;

import com.example.demo.domain.dtos.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/login")
    public void login(){
        log.info("GET /login......");
    }

    // 확인방법 1 - Authentication---------------------------------

//    @GetMapping("/user") //유저 정보 확인하기 Authentication
//    // 이 수준에서 비밀번호를 확인하는 방법 Model로 받아 user.html에서 확인(타임리프 필요)
//    public void user(Authentication authentication, Model model){
//        log.info("GET /user..."+authentication);
//        log.info("name..."+authentication.getName());
//        log.info("principal..."+authentication.getPrincipal());
//        log.info("authorities..."+authentication.getAuthorities());
//        log.info("details...."+authentication.getDetails());
//        log.info("credential..."+authentication.getCredentials());
//
//        model.addAttribute("auth_1",authentication);
//    }
//    ----------------------------------------------------------------

//    확인방방법 2 - Context를 통해 꺼내오는 작업
    @GetMapping("/user")
    public void user(Model model){

        Authentication authentication =
        SecurityContextHolder.getContext().getAuthentication();
        
        log.info("GET /user..."+authentication);
        log.info("name..."+authentication.getName());
        log.info("principal..."+authentication.getPrincipal());
        log.info("authorities..."+authentication.getAuthorities());
        log.info("details...."+authentication.getDetails());
        log.info("credential..."+authentication.getCredentials());

        model.addAttribute("auth_1",authentication);
    }

    //확인방법 3 - Authentication's Principal만 꺼내와 연결
    @GetMapping("/manager")
    public void manager(@AuthenticationPrincipal Principal principal){
        log.info("GET /manager......"+principal);

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
