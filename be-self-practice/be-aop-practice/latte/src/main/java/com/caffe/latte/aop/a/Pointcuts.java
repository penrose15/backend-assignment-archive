package com.caffe.latte.aop.a;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    //포인트 컷을 공용으로 사용하기 위해 외부 클래스로 외부에 모아둘 수 있습니다.
    @Pointcut("execution(* com.caffe.latte.aop..*(..))")
    public void allOrder(){}

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService() {}

    @Pointcut("allOrder() && allService()")
    public  void orderAndService(){}
}
