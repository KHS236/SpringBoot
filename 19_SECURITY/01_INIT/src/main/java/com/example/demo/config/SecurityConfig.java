package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //시큐리티 설정 어노테이션
public class SecurityConfig {


    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

        //csrf 비활성화 (비활성화하지않으면 logout 요청은 기본적으로 POST방식을 따름
        http.csrf((config)->{config.disable();});

        //권한처리(authorize==인가처리)
        http.authorizeHttpRequests((auth)->{
            //로그인 페이지 권한 허용
            auth.requestMatchers("/login","/logout","/join").permitAll();
//            기본 페이지 권한 허용
            auth.requestMatchers("/").permitAll();
            // 기본 페이지 USER 허용
            auth.requestMatchers("/").hasRole("USER");
            // 매니저 전용 페이지 매니저 허용
            auth.requestMatchers("/manager").hasRole("MANAGER");
            // 어드민 전용 페이지 어드민 허용
            auth.requestMatchers("/admin").hasRole("ADMIN");

            auth.anyRequest().authenticated();
        });
        
        //로그인
        //start.spring.io에서 Security보면 버전 3점대부터 모두 람다식으로 처리
        http.formLogin((login)->{
            login.permitAll(); //모두가 접근 가능
            login.loginPage("/login");
        });
        //로그아웃
        http.logout((logout)->{
            logout.permitAll(); //모두가 접근 가능
        });
        //예외처리

        //(기타 등등)Etc..

        return http.build();
    }

    //임시계정생성
//    @Bean
//    UserDetailsService users() {
//        UserDetails user = User.withUsername("user")
//                .password("{noop}1234")   // 비밀번호 인코딩 없음 (실습용)
//                .roles("USER")            // ROLE_USER
//                .build();
//
//        UserDetails manager = User.withUsername("manager")
//                .password("{noop}1234")
//                .roles("MANAGER")         // ROLE_MANAGER
//                .build();
//
//        UserDetails admin = User.withUsername("admin")
//                .password("{noop}1234")
//                .roles("ADMIN")           // ROLE_ADMIN
//                .build();
//
//        return new InMemoryUserDetailsManager(user, manager, admin);
//    }
    //noop == 비밀번호 평문화
//스프링 시큐리티 5 버전부터는 비밀번호 저장시 반드시 인코딩 방식 명시를 요구해요
//그렇지 않으면 실행 시 에러가 납니다
//따라서 평문 비밀번호를 테스트용으로 간단하게 쓰려면 {noop}을 붙여야 합니다

    //패스워드 암호화작업(해시값생성)에 사용되는 Bean
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
