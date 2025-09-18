package com.example.demo.Dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemoDto {

    private long id;
    private String text;
    private String writer;
    private LocalDateTime createAt;




}
