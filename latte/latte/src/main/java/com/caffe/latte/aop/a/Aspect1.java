package com.caffe.latte.aop.a;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class Aspect1 {

    @Around("execution(* com.caffe.latte.aop..*(..))")
    //com.caffe.latte 패키지와 하위 패키지 .. 를 지정한 AspectJ포인트 컷 표현식입니다.
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        //이 메서드가 어드바이스가 된다
        log.info("log -> {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
