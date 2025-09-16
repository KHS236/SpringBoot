package com.example.demo.Domain.Common.Service;

import com.example.demo.Domain.Common.Dao.MemoDao;
import com.example.demo.Domain.Common.Dto.MemoDto;
import com.example.demo.Domain.Common.Mapper.MemoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoService {
    @Autowired
    private MemoDao memoDao;

    //기존 dao가 아닌 애초에 memoMapper에 연결해서 사용하는 방법
    @Autowired
    private MemoMapper memoMapper;
    //MYBATIS용
    public boolean memoRegistration(MemoDto dto) throws Exception {
        long result = memoMapper.insert(dto);
        return result>0;
    }


//    public boolean memoRegistration(MemoDto dto) throws Exception {
//        Long result = memoDao.insert(dto);
//        return result>0;
//    }



}
