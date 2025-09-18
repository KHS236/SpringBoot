package com.example.demo.Restful.Opendata;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/APARTMENT")
public class OpenData04Controller {
    private String server = "https://apis.data.go.kr/6270000/dbmsapi02/getRealtime02";
    private String serviceKey = "xYZ80mMcU8S57mCCY/q8sRsk7o7G8NtnfnK7mVEuVxdtozrl0skuhvNf34epviHrru/jiRQ41FokE9H4lK0Hhg==";
    private String LAWD_CD;
    private String DEAL_YMD;

    @GetMapping(value = "/{LAWD_CD}/{DEAL_YMD}")
    public void get(
            @PathVariable(value = "LAWD_CD", required = true) String LAWD_CD,
            @PathVariable(value = "DEAL_YMD", required = true) String DEAL_YMD,
            Model model
    ) {
        log.info("GET /INCIDENT....LAWD_CD : " + LAWD_CD + " DEAL_YMD : " + DEAL_YMD);
        this.LAWD_CD = LAWD_CD;
        this.DEAL_YMD = DEAL_YMD;

        // 파라미터 설정( service Key 포함)
//        String url = UriComponentsBuilder.fromHttpUrl(server)
//                .queryParam("serviceKey",serviceKey)
//                .queryParam("pageNo",pageNo)
//                .queryParam("numOfRows",numOfRows)
//                .toUriString();
//        System.out.println(url);

        String url = server;
        url += "?serviceKey=" + serviceKey;
        url += "&LAWD_CD=" + LAWD_CD;
        url += "&DEAL_YMD=" + DEAL_YMD;


        RestTemplate restTemplate = new RestTemplate();

//        // 요청 헤더 설정(x)
//        HttpHeaders header = new HttpHeaders();
//        header.add("key","value");
//        // 요청 바디 설정(x)
//        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
//        params.add("","");
//
//        HttpEntity< MultiValueMap<String,String>  > entity = new HttpEntity<>(params,header);

        // 요청 후 응답 확인
        ResponseEntity<Root> response =
        restTemplate.exchange(url, HttpMethod.GET,null,Root.class);

        //REST TYPE -> Class Type 변환
        System.out.println(response.getBody());

        //확인

    }


    // RESPONSE CLASS
    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class header {
        @XmlElement
        public int resultCode;
        @XmlElement
        public String resultMsg;
    }
    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class item {
        @XmlElement
        public Object aptDong;
        @XmlElement
        public String aptNm;
        @XmlElement
        public int buildYear;
        @XmlElement
        public Object buyerGbn;
        @XmlElement
        public Object cdealDay;
        @XmlElement
        public Object cdealType;
        @XmlElement
        public double dealAmount;
        @XmlElement
        public int dealDay;
        @XmlElement
        public int dealMonth;
        @XmlElement
        public int dealYear;
        @XmlElement
        public Object dealingGbn;
        @XmlElement
        public Object estateAgentSggNm;
        @XmlElement
        public double excluUseAr;
        @XmlElement
        public int floor;
        @XmlElement
        public int jibun;
        @XmlElement
        public String landLeaseholdGbn;
        @XmlElement
        public Object rgstDate;
        @XmlElement
        public int sggCd;
        @XmlElement
        public Object slerGbn;
        @XmlElement
        public String umdNm;

    }
    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class items {
        @XmlElement
        public List<item> item;
    }
    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class body {
        @XmlElement
        public items items;
        @XmlElement
        public int numOfRows;
        @XmlElement
        public int pageNo;
        @XmlElement
        public int totalCount;
    }
    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class root {
        @XmlElement
        public header header;
        @XmlElement
        public body body;
    }

}