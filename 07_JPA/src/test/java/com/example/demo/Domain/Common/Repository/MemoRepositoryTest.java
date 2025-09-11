package com.example.demo.Domain.Common.Repository;

import ch.qos.logback.core.CoreConstants;
import com.example.demo.Domain.Common.Entity.Memo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @BeforeEach //테스트 사전에 할 것들을 선정하는 어노테이션
    public void post1000(){
        if(memoRepository.count()==0){
            for (int i=0;i<1000;i++){
                memoRepository.save(new Memo(null,"TEXT-"+i,"WRITER-"+i,LocalDateTime.now()));
            }
        }
    }
    @Test
    public void t6() {
        System.out.println(memoRepository.count());

        Pageable pageable = PageRequest.of(0,10);
        Page<Memo> page = memoRepository.findAll(pageable);

        System.out.println("현재페이지번호:"+page.getNumber());
        System.out.println("한페이지에 표시할 건수:"+page.getSize());
        System.out.println("총게시물 개수:"+page.getTotalElements());
        System.out.println("총페이지 개수:"+page.getTotalPages());
        System.out.println("첫번째 페이지인지 여부:"+page.isFirst());
        System.out.println("다음페이지가 있는지 여부:"+page.hasNext());
        System.out.println("이전페이지가 있는지 여부:"+page.hasNext());

        List<Memo> list = page.getContent();
        list.forEach(System.out::println);
        System.out.println("-----------------");

        Page<Memo> nextPage = memoRepository.findAll(page.nextPageable());


    }








}