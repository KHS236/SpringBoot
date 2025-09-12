package com.example.demo.Config;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MybatisConfigTest {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Test
    public void t1() {
        assertNotNull(sqlSessionFactory); //세션팩토리가 정상적으로 만들어졌는지 확인
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //세션 꺼내기 : sqlSession으로 받아주기
        Connection conn = sqlSession.getConnection();
        //커넥션 꺼내오기 (커넥션 객체로 받아진다)
        assertNotNull(conn); //커넥션이 정상적으로 받아졌는지 확인
        //아래와 같이 쿼리문 사용 가능
//        sqlSession.delete("statement(쿼리문)");
//        sqlSession.insert("statement(쿼리문)");
//        sqlSession.update("statement(쿼리문)");
//        sqlSession.select("statement(쿼리문)");


//매퍼로 이동!


    }




}