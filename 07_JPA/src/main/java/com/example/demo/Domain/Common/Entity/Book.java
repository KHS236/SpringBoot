package com.example.demo.Domain.Common.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    private Long bookCode;
//    @Column(name="bookname") //컬럼명 직접 지정해주는법
    private String bookName;
    private String publisher;
    private String isbn;

//관련된 레파지토리 만들기


}
