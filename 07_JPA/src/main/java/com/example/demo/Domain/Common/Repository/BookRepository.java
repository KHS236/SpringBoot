package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//북레파지토리 JPA레파지토리 상속 , <엔터티 명 , 프라이머리키열 자료형>
//다음 테스트 ctlr + shfit + T
public interface BookRepository extends JpaRepository <Book,Long> {
//    List<Book> findBy검색할 조건절 컬럼명
//                  (그대로 복붙해오기+앞글자 대문자로)
    List<Book> findByBookName(String bookName);
    List<Book> findByPublisher(String publisher);
    List<Book> findByIsbn(String isbn);
    List<Book> findByBookNameAndPublisher(String bookName,String publisher);
    List<Book> findByBookNameContains(String bookName);


}
