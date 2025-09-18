package com.example.demo.RestController;


import com.example.demo.Dto.MemoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/rest")
public class RestTestController {

    @GetMapping(value="/getText",produces=MediaType.TEXT_PLAIN_VALUE)
    //페이지가 아닌 데이터를 주는 것 (반환자료형 설정해주기) └텍스트 데이터로 받겠따
    public String t1() {
        log.info("GET /rest/getText...");
        return "HELLO WORLD";


    }
    //Json데이터로 던지기
    @GetMapping(value="/getJson",produces=MediaType.APPLICATION_JSON_VALUE)
    //반환자료형 MemoDto                              └Json데이터로 받겠따
    public MemoDto t2() {
        log.info("GET /rest/getJson...");
        return new MemoDto(1L, "TEXT-1", "WRITER-1", LocalDateTime.now());
    }

    @GetMapping(value="/getXml",produces=MediaType.APPLICATION_XML_VALUE)
    //반환자료형 MemoDto                              └Json데이터로 받겠따
    public MemoDto t3() {
        log.info("GET /rest/getXml...");
        return new MemoDto(1L, "TEXT-1", "WRITER-1", LocalDateTime.now());
    }

    //XML로 프론트에 내용 전달 (리스트형태로)
    @GetMapping(value="/getXmlList",produces=MediaType.APPLICATION_XML_VALUE)
    //반환자료형 MemoDto                              └XML데이터로 받겠따
    public List<MemoDto> t4() {
        log.info("GET /rest/getXmlList...");
        List<MemoDto>list = new ArrayList<>();
        for(long i=0;i<=100;i++){
            MemoDto tmp = new MemoDto(i,"내용-"+i,"작성자-"+i,LocalDateTime.now());
            list.add(tmp);
        }
        return list;
    }
    //{show} true일경우 표시 false일 경우 표시X
    @GetMapping(value="/getXmlList2/{show}",produces=MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<MemoDto> > t5
    (@PathVariable("show")boolean show) {
        if (show) {
            log.info("GET /rest/getXmlList2...");
            List<MemoDto> list = new ArrayList<>();
            for (long i = 0; i <= 100; i++) {
                MemoDto tmp = new MemoDto(i, "내용-" + i, "작성자-" + i, LocalDateTime.now());
                list.add(tmp);
            }
//            return new ResponseEntity("데이터값","상태정보값");
//            return new ResponseEntity(list , HttpStatus.OK);//정상인 경우 OK
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        return new ResponseEntity(null,HttpStatus.BAD_GATEWAY);
    }
}
