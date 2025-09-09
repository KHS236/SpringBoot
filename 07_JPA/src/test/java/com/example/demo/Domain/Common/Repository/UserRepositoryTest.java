package com.example.demo.Domain.Common.Repository;

import com.example.demo.Domain.Common.Entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
//데모 어플리케이션 실행으로 유저테이블 만들기
@SpringBootTest
class UserRepositoryTest {
    //userRepository 오토와이어드 *주입 걸기
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void t1() {
//        List<User> list = userRepository.selectAllByRole("ROLE_USER");
//        list.forEach(System.out::println);

        /*ROLE_USER이면서 password가 1111인 행 찾기*/
//        List<User> list = userRepository.selectAllByRoleAndPwd("ROLE_USER","1111");
//        list.forEach(System.out::println);

//        List<User> list = userRepository.selectAllByRole_2("ROLE_ADMIN");
//        list.forEach(System.out::println);

        /*이 행위가 단위테스트라네요*/

        //"u"를 포함하는!
        List<User> list = userRepository.selectAllLikeUsername("e");
        list.forEach(System.out::println);


    }


    
    
}