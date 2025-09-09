package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//레파지토리 어노테이션 꼭 쓰기
@Repository
public interface UserRepository extends JpaRepository<User,String> {

    //쿼리문 그냥 대소문자 잘 구별하기 문법 외우기
    @Query("SELECT u FROM User as u where u.Role=?1")
    List<User> selectAllByRole(String Role);

    @Query("SELECT u FROM User as u where u.Role=?1 and password=?2")
    List<User> selectAllByRoleAndPwd(String Role, String password);

                                        //JPQL에서 사용하는 변수명처리(:명)
    @Query("SELECT u FROM User as u where u.Role=:Role")
//    List<User> selectAllByRole_2(@Param("위에 있는 =:변수명 넣기") String Role);
    List<User> selectAllByRole_2(@Param("Role") String Role);

    @Query("SELECT u FROM User as u where u.username like concat('%',:user,'%')")
    List<User> selectAllLikeUsername(@Param("user") String username);

}
