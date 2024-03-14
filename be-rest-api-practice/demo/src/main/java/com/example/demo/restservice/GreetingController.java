package com.example.demo.restservice;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String template = "hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    //AtomicLong는 Long 자료형을 가지고 있는 Wrapping 클래스 이다.

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue = "world") String name) {
        //requestParam 으로 키값으로 name을 받고 키값이 없으면 defaultValue로 대체함
        return new Greeting(counter.incrementAndGet(),String.format(template,name));
        //incrementAndGet는 +=1을 해주는 메서드이다
        //Greeting을 파라미터로 받아 id를 1씩 증가 시키고 name을 content로 받음
    }

}
