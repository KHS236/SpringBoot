package com.example.demo.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
//MemoService 클래스 내 함수가 실행되기 전에 사용할 예정이면 @Before
// 함수가 실행된 후 사용할 예정이면 @After 어노테잇션 사용
public class LoggingAspect {
    /*
//    @Before("execution(반환자료형 위치)")
    @Before("execution(* com.example.demo.Model.Service.MemoService.addMemo(..))")
    //MemoService 안에 addMemo가 실행되기 '전'에 실행할 예정
    public void loggingBefore(JoinPoint joinPoint)
    {
        log.info("[AOP] BEFORE... "+ joinPoint);
        // joinPoint
        //AOP가 기본적으로 Proxy 어쩌구를 사용하는데
        //MemoService에 특정한 위치에 접근 (특정 위치에 before위치 접근)
    }
    @After("execution(* com.example.demo.Model.Service.MemoService.addMemo(..))")
    public void loggingAfter(JoinPoint joinPoint)
    {
        log.info("[AOP] After... "+ joinPoint);
    }
*/
//    @Around("target")
    //execution("execution(* com.example.demo.Model.Service.MemoService.addMemo(..))")
    //target : MemoService.addMemo 함수
    //execution("execution(* com.example.demo.Model.Service.MemoService.*(..))")
    //└ MemoService 안의 모든 Method
    //execution("execution(* com.example.demo.Model.Service.*.*(..))")
    //└Service 패키지 안의 모든 Service안의 모든 Method
    @Around("execution(* com.example.demo.Model.Service.*.*(..))")
    public Object loggingaround(ProceedingJoinPoint pjp) throws Throwable {
        //BEFORE 처리코드
        Long start_time = System.currentTimeMillis();
        log.info("[AOP] AROUND BEFORE");

        //타겟 함수 실행
        Object returnValue = pjp.proceed();
        //proceed 함수를 쓰는 순간 위 어노테이션에 반환한 함수(target)를 실행
        log.info("타겟 함수 리턴 값 : " + returnValue);

        //After 처리 코드
        log.info("[AOP] AROUND AFTER");
        Long end_time = System.currentTimeMillis();
        log.info("[AOP] 소요시간 : " + (end_time-start_time) + " ms");
        return returnValue;
    }
}
