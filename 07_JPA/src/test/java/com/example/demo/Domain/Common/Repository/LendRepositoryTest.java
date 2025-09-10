package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Book;
import com.example.demo.Domain.Common.Entity.Lend;
import com.example.demo.Domain.Common.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LendRepositoryTest {

    @Autowired
    private LendRepository lendRepository;
    /*랜드레파지토리(관계형테이블레파지토리)만들 땐 관련 된 테이블 레파지토리도 다 가져와야한다.*/
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;


    @Test
    public void t1 () {
//        List<Lend> list = lendRepository.findAll();
//        list.forEach(System.out::println);

        //Insert 하려면 일단 유저랑 북의 정보가 필요
        //어떤 유저가 어떤 책을 대여할지를 저장하기 위해 유저정보(유저엔티티)부터 가져오기
//        User user = userRepository.findById("user1").get();
        //마찬가지로 북도 가져오기
//        Book book = bookRepository.findById(1L).get();
//        Lend lend = Lend.builder()
//                .id(null)
//                .user(user) //바로위에서 찾아온 user랑 book
//                .book(book)
//                .build();
//        lendRepository.save(lend); //오토제너레이트 된 ID값이 알아서 들어갈 것
        //수정이나 삭제도 user랑 book 구해와서 하는 것임
    }

    @Test
    public void t2(){
//        List<Lend> list = lendRepository.findAll();
//        list.forEach(System.out::println);
        //user1 bookCode 1L 대여
        User user = userRepository.findById("user1").get();
        Book book = bookRepository.findById(1L).get();
        Lend lend = Lend.builder()
                .id(null)
                .user(user)
                .book(book)
                .build();
        lendRepository.save(lend);
    }
    @Test
    public void t3(){
//        List<Lend> list = lendRepository.findAll();
//        list.forEach(System.out::println);
        //user1 bookCode 2L 대여
        User user = userRepository.findById("user1").get();
        Book book = bookRepository.findById(2L).get();
        Lend lend = Lend.builder()
                .id(null)
                .user(user)
                .book(book)
                .build();
        lendRepository.save(lend);
    }
    @Test
    public void t4(){
//        List<Lend> list = lendRepository.findAll();
//        list.forEach(System.out::println);
        //user2 bookCode 3L 대여
        User user = userRepository.findById("user2").get();
        Book book = bookRepository.findById(3L).get();
        Lend lend = Lend.builder()
                .id(null)
                .user(user)
                .book(book)
                .build();
        lendRepository.save(lend);
    }
    @Test
    public void t5(){
//        List<Lend> list = lendRepository.findAll();
//        list.forEach(System.out::println);
        //user3 bookCode 4L 대여
        User user = userRepository.findById("user3").get();
        Book book = bookRepository.findById(4L).get();
        Lend lend = Lend.builder()
                .id(null)
                .user(user)
                .book(book)
                .build();
        lendRepository.save(lend);
    }
    @Test
    public void t6(){
//        List<Lend> list = lendRepository.findAllLendsByUser("user1");
//        list.forEach(System.out::println);
//
    }
    @Test
    public void t7(){
        List<Lend> list = lendRepository.findAllLendsByBook("TEST_BOOK");
        list.forEach(System.out::println);
    }

    @Test
    @Transactional(rollbackFor=Exception.class)//LAZY로 설정시 TX설정이 필요하다
    public void t8(){
        System.out.println("---------FETCH TEST START");
        Optional<Lend> lendOptional = lendRepository.findById(1L);
        System.out.println("---------lendRepository.findById(1L) invoke!---------");
        Lend lend = lendOptional.get();
        System.out.println("---------lendOptional.get() invoke!---------");
        User user = lend.getUser(); //!
        System.out.println(user);
        System.out.println("---------lend.getUser() invoke!---------");
        Book book = lend.getBook(); //!
        System.out.println("---------lend.getBook() invoke!---------");

        System.out.println("---------FETCH TEST END");
    }

}