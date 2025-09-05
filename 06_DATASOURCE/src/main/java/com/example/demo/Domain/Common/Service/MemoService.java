package com.example.demo.Domain.Common.Service;


//비즈니스 영역 매우 중요

import com.example.demo.Domain.Common.Dao.MemoDao;
import com.example.demo.Domain.Common.Dto.MemoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class MemoService {
    @Autowired
    private MemoDao memoDao;


    public boolean memoRegistration(MemoDto dto) throws Exception {
        int result = memoDao.insert(dto);


        return result>0;
    }




}
