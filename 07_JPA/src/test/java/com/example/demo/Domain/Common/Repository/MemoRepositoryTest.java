package com.example.demo.Domain.Common.Repository;

import ch.qos.logback.core.CoreConstants;
import com.example.demo.Domain.Common.Entity.Memo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoRepositoryTest {
    @Autowired
    private MemoRepository memoRepository;

//    Insert
    @Test
    public void test1(){
        Memo memo = new Memo(null,"내용2","test2@test.com", LocalDateTime.now());
        memoRepository.save(memo);
        System.out.println("ID: " + memo.getId());//새로 추가된 ID 확인

    }

    //Update (Insert랑 같음 키만 지정해주고 내용 수정하면 1번에 바뀐내용 들어감)
    @Test
    public void test2(){
        Memo memo = new Memo(1L,"수정된내용","tests@test.com", LocalDateTime.now());
        memoRepository.save(memo);
        System.out.println("ID: " + memo.getId());
    }

    //Delete //2번키 행 삭제
    @Test
    public void test3(){
        memoRepository.deleteById(2L);
    }


    //조회
    @Test
    public void test4(){
        Optional<Memo> memoOptional = memoRepository.findById(3L);
        if(memoOptional.isPresent()){
            Memo memo = memoOptional.get();
            System.out.println(memo);
        }
    }

    //전체 조회
    @Test
    public void test5(){
        List<Memo> list =  memoRepository.findAll();
        list.forEach(System.out::println); //람다식에서 참조연산자로 더 줄임// 다 꺼내서 보여줌
        //(el)->{System.out.println(el);}
        //el->System.out.println(el);
        //System.out::println;

    }









}