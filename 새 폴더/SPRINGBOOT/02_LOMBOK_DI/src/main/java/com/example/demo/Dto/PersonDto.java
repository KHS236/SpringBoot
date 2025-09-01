package com.example.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 생성자 주입

//게터 세터 투스트링 디폴트생성자 모든인자생성자
//@Getter
//@Setter
//@ToString
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PersonDto {
    private String name;
    private int age;
    private String addr;

}
