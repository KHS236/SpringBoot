package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.Lend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LendRepository extends JpaRepository <Lend,Long> {

    //join작업
    //검색한 유저가 도서를 대여한 현황을 확인하기
    @Query("SELECT l FROM Lend AS l JOIN FETCH l.user WHERE l.user.username=:username")
    List<Lend> findAllLendsByUser(@Param("username") String username);

    @Query("SELECT l FROM Lend AS l JOIN FETCH l.book WHERE l.book.bookName=:bookName")
    List<Lend> findAllLendsByBook(@Param("bookName") String bookName);


}
