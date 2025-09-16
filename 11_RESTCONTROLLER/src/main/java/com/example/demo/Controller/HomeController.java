package com.example.demo.Controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    //기본 컨트롤러, 기본 페이지를 표시하는 매핑
    @GetMapping("/")
    public String home(){
        log.info("GET /...");
        return "index";
    }




}
