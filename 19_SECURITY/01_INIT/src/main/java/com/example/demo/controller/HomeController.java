package com.example.demo.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class HomeController {

    @GetMapping("/")
    public String home () {
        log.info("GET /....");
        return "index";
        //기본 경로(//resources/templates)를 요청시 index페이지 리턴
    }

    

}
//임시 비번 아이디 로그아웃
//user
//b2f258c3-e001-4b5e-8c91-970bbcc6d36e
//http://localhost:8090/logout



