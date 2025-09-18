package com.example.demo.Restful.Opendata;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

//대구광역시_돌발 교통정보 조회 서비스(신)
@RestController
@Slf4j
@RequestMapping("/")
public class OpenData01Controller {
    private String server = "https://apis.data.go.kr/6270000/service/rest/dgincident";
    private String serviceKey = "2ca0ff73db07d5a44a18235d39c0211316bcad9236797f2be808eb6f0b7bd3cc";
    private String pageNo;
    private String numOfRows;

    @GetMapping(value = "/{pageNo}/{numOfRows}")
    public void get(
            @PathVariable(value = "pageNo",required = true) String pageNo,
            @PathVariable(value = "numOfRows",required = true) String numOfRows

    )
    {
        log.info("GET /INCIDENT....pageNo : " +pageNo+" amount : " + numOfRows);
        this.pageNo = pageNo;
        this.numOfRows = numOfRows;

        // 파라미터 설정( service Key 포함)
        String url = UriComponentsBuilder.fromHttpUrl(server)
                .queryParam("serviceKey",serviceKey)
                .queryParam("pageNo",pageNo)
                .queryParam("numOfRows",numOfRows)
                .toUriString();
//        String url = server;
//            url+="?serviceKey=" + serviceKey;
//            url+="&pageNo=" + pageNo;
//            url+="&numOfRows="+numOfRows;

        RestTemplate restTemplate = new RestTemplate(); //요청 준비

        //요청 헤더 설정(x)
//        HttpHeaders header = new HttpHeaders();
//        header.add("key","value");

        //요청 바디 설정(x)
//        Map<String,String> params = new HashMap<>();
//        params.put("","");
//        LinkedMultiValueMap<String,String> params = new LinkedMultiValueMap<>()// HashMap<>();
//        params.add("","");
//        HttpEntity<Map<String,String>> entity = new HttpEntity<>(params,header);
//        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(params,header);
        //요청 후 응답 확인
//        restTemplate.exchange("url","요청METHOD","entity","반환자료형")
        ResponseEntity<String> response =
                restTemplate.exchange(url, HttpMethod.GET,null,String.class);

        System.out.println(response);
        //REST TYPE -> Class Type 변환







    }
}
