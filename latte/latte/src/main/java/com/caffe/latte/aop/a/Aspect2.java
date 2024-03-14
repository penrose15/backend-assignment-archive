package com.caffe.latte.aop.a;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Aspect2 {

    @Pointcut("execution(* com.caffe.latte.aop..*(..))")
    private void allOrder() {}

    @Around("allOrder()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("log -> {}", joinPoint.getSignature());
    return joinPoint.proceed();
    }
}
