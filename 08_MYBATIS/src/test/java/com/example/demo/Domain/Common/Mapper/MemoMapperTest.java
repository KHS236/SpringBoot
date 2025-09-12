package com.example.demo.Domain.Common.Mapper;

import com.example.demo.Domain.Common.Dto.MemoDto;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemoMapperTest {

    @Autowired
    private MemoMapper memoMapper;

//    @Test
//    public void post1000() {
//        for(int i=1;i<1000;i++){
//            memoMapper.insert(new MemoDto(null,"content-"+i,"writer-"+i,LocalDateTime.now(),null));
//        }
//    }

    @Test
    public void t1 () {
        MemoDto dto = new MemoDto(1L,"내용1","작성자1", LocalDateTime.now(),null);
        memoMapper.insert(dto);

    }

    @Test
    public void t2() {
        MemoDto dto = new MemoDto(1L,"내용1-UPDATE","작성자1-UPDATE", LocalDateTime.now(),null);
        memoMapper.update(dto);
    }

    @Test
    public void t3() {
        memoMapper.delete(1L);
    }

    @Test
    public void t4 () {
        MemoDto dto = new MemoDto(null,"내용1","작성자1", LocalDateTime.now(),null);
        memoMapper.insert(dto);
        System.out.println(dto);

    }

    @Test
    public void t5 () {
//        List<MemoDto> list = memoMapper.selectAll();
//        list.forEach(System.out::println);

        List<MemoDto> list = memoMapper.selectAllContains("writer","2");
        list.forEach(System.out::println);

    }

    @Test
    public void t6 () {
        List<Map<String,Object>> list =
        memoMapper.selectAllWithResultMap();
        list.forEach((map)->{
            System.out.println(map);
        });

    }

    @Test
    public void t7 () {
        MemoDto dto = new MemoDto(2000L,"내용1","작성자1", LocalDateTime.now(),null);
        memoMapper.insert(dto);
    }


    @Test
    public void t8 () {
        MemoDto dto = new MemoDto(2000L,"내용1-UPDATE","작성자1-UPDATE", LocalDateTime.now(),null);
        memoMapper.update(dto);
    }

    @Test
    public void t9 () {
        memoMapper.delete(2000L);
    }


}