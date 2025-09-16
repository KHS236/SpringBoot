package com.example.demo.Domain.Common.Dao;

import com.example.demo.Domain.Common.Dto.MemoDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;

@Repository
public class MemoDao {

    @Autowired
    private DataSource dataSource;

    //기존 DAO에 연결해서 사용하는 방법
    @Autowired
    private SqlSession sqlSession;
    private String NAMESPACE = "com.example.demo.Domain.Common.Mapper.MemoMapper.";//반드시 마지막 위치에 . 찍어주기
    //MYBATIS 사용시 연결용
    //굳이 dao를 한번 더 연결해서 사용하는 이유
    // 이 전에 dao에서 작업하던 내용이 있을경우 가져와서 연결하는 경우가 있음
    public Long insert(MemoDto dto) throws SQLException {
//        sqlSession.insert(NAMESPACE+"함수명",파라미터);//커스텀insert가 아닌 sqlSession이 가지고 있는 insert
        sqlSession.insert(NAMESPACE+"insert",dto);//NAMESPACE 경로의 파일에서 insert함수(파라미터dto)로 전달했을때
        System.out.println(dto);
        return dto.getId();
    }

//    public int insert(MemoDto dto) throws SQLException {
//        Connection conn = dataSource.getConnection();
//        PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(?,?,?,?)");
//        pstmt.setLong(1,dto.getId());
//        pstmt.setString(2,dto.getText());
//        pstmt.setString(3,dto.getWriter());
//        pstmt.setString(4, dto.getCreateAt().toString());
//        int result = pstmt.executeUpdate();
//        return result;
//    }
    
    
    
    
    
    
    
    
    
    
}
