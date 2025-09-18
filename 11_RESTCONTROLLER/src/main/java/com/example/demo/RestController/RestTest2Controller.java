package com.example.demo.RestController;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/rest2")
public class RestTest2Controller {

    @GetMapping("/test1") //rest2안에 test1.html이 있는지 확인
    @ResponseBody //컨트롤러 안에 이 함수만 비동기처리를 하기위한 어노테이션
    public String t1() {
        log.info("GET /rest2/test1...");
        return "HELLOWORLD"; //HELLOWORLD라는 페이지를 찾음
    }














}
