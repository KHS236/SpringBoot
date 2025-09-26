package com.example.demo.Restful.C03Kakao;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/kakao/pay")
public class KakaoPayController {

    @GetMapping("/req")
    @ResponseBody
    public void req() {
        log.info("GET /kakao/pay/req.......");
    }


}
