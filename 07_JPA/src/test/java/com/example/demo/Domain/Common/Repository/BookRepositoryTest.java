package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
//SpringBootTest 빼먹지 않기
class BookRepositoryTest {

//테스트 함수만 실행해서 오류 내는 페이지 나오면 주석처리
    @Autowired
    private BookRepository bookRepository;

    @DisplayName("--기본CRUD 확인--")
    @Test
    public void t1() {
        Book book = Book.builder()
                .bookCode(1L)
                .bookName("이것이 자바다")
                .publisher("한빛 미디어")
                .isbn("1111-1111")
                .build();

        //INSERT
//        bookRepository.save(book);

        //UPDATE
//        bookRepository.save(book);

        //DELETE
//        bookRepository.deleteById(1ㅣ);

//        SELECT
//        Optional<Book> bookOptional =
//        bookRepository.findById(1L);
//        Book rBook = null;
//        if (bookOptional.isPresent())
//        {
//            rBook = bookOptional.get();
//            System.out.println(rBook);
//        }

        //SelectAll
//        List<Book> list = bookRepository.findAll();
//        list.forEach(System.out::println);

    }
    @DisplayName("-- 함수명명법 TEST --")
    @Test
    public void t2() {
//        List<Book> list=bookRepository.findByBookName("a");
//        list.forEach(System.out::println);
//        }
//    @Test
//    public void t3(){
//        List<Book> list=bookRepository.findByPublisher("b");
//        list.forEach(System.out::println);
//        }
//    @Test
//    public void t4(){
//        List<Book> list=bookRepository.findByIsbn("c");
//        list.forEach(System.out::println);
//        }
        /*"d"를 포함하는 거 찾기*/
        List<Book> list = bookRepository.findByBookNameContains("d");
        list.forEach(System.out::println);
    }





}