package com.caffe.latte;

import com.caffe.latte.aop.a.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import com.caffe.latte.aop.OrderRepository;
import com.caffe.latte.aop.OrderService;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@Import(Aspect6.class)
//@Import({Aspect5.LogAspect.class, Aspect5.TxAspect.class})
@Slf4j
@SpringBootTest
public class AopTest {

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void aopInfo() {
        log.info("isAopProxy, orderService={}", AopUtils.isAopProxy(orderService));
        log.info("isAopProxy, orderRepository={}", AopUtils.isAopProxy(orderRepository));
    }

    @Test
    void success() {
        orderService.orderItem("itemA");
    }

    @Test
    void exception() {
        assertThatThrownBy(() -> orderService.orderItem("ex")) //만약 아이템 아이디가 ex라면 IllegalStateException가 뜨므로
                .isInstanceOf(IllegalStateException.class); // 테스트 통과
    }
}