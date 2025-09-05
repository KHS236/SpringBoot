package com.example.demo.Domain.Common.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//테이블 만들기
@Entity
@Table(name="memo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Memo {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    //직접 입력할 필요 없이 알아서 insert요청으로 생성
    private Long id;
    @Column(length=1024)//컬럼에 속성 추가
    private String text;
    @Column(length=100,nullable = false)
    private String writer;
    private LocalDateTime createAt;

    
}
