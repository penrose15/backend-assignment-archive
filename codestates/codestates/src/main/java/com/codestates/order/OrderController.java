package com.codestates.order;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/v1/order")
public class OrderController {

    @PostMapping
    public ResponseEntity postOrder(@RequestParam("memberId") long memberId,
                            @RequestParam("coffeeId") long coffeeId){
        //커피 주문 정보를 등록해주는 핸들러 메서드
        //memberId가 회원 식별자, coffeeId는 커피 식별자
        //식별자란 어떤 데이터를 식별할 수 있는 고유값(Primary key)
        System.out.println("# memberId : "+ memberId);
        System.out.println("# coffeeId : "+ coffeeId);

//        String response =
//                "{\""+
//                        "memberId\":\"" + memberId + "\","+
//                        "\"coffeeId\":\"" + coffeeId +"\"" +
//                        "}";
//        return response;
        Map<Object, Object> map = new HashMap<>();
        map.put("memberId",memberId);
        map.put("coffeeId",coffeeId);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }
    @GetMapping("/{order-id}")
    public ResponseEntity.BodyBuilder getOrder(@PathVariable("order-id") long orderId) {
        //not implement
        HttpHeaders headers = new HttpHeaders();
        headers.add("Custom","bar");
        return ResponseEntity.ok().headers(headers);
    }

    @GetMapping
    public ResponseEntity getOrders() {
        System.out.println("# get Orders");
        //not implement

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
