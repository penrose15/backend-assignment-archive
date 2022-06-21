package aop.practice.AOP;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
@Aspect
public class Aop {
    private long curLong;

    @Pointcut("execution(* aop.practice..*(..))")
    public void time() {}

    @Before("time()")
    public void thisTime() {
        long curLong = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String curTime = sdf.format(new Date(curLong));
        System.out.println(curTime);
    }
    @After("time()")
    public void logging(JoinPoint joinPoint){
        log.info("log -> {} ", joinPoint.getSignature());
    }

}
