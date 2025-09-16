package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dto.MemoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TxTestServiceTest {
    @Autowired
    private TxTestService txTestService;

    @Test
    public void t1() throws Exception{
        txTestService.addMemo();
    }

    @Test
    public void t2() throws Exception{
        txTestService.addMemoWithMybatis(new MemoDto(9990L,"a","a",null));
    } //TX처리하지 않은 경우 (예외발생 후 tbl_memo TX처리 안 돼있음)

    @Test
    public void t3() throws Exception{
        txTestService.addMemoWithMybatisTx(new MemoDto(8990L,"a","a",null));
    }//TX처리된 경우 (예외발생 후 tbl_memo 기존 상태로 원복)
    
//    스프링부트 TX 옵션 트랜잭션 전파 속성(트랜잭션을 중첩으로 처리하는 옵션 자습필요)



}