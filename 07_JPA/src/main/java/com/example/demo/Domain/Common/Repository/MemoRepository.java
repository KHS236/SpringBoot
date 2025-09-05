package com.example.demo.Domain.Common.Repository;


import com.example.demo.Domain.Common.Entity.Memo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface MemoRepository extends JpaRepository<CRUD대상엔티티,프라이머리자료형> {
public interface MemoRepository extends JpaRepository<Memo,Long> {


    //메서드 명명법
    //JPQL(SQL문 작성)


}
