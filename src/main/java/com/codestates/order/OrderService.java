package com.codestates.order;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Orders createOrder(Orders order) {
        // TODO 회원이 존재하는지 조회해야 됨
        // TODO 커피가 존재하는지 조회해야 됨
        order.setCreatedAt(LocalDateTime.now());
        return orderRepository.save(order);
    }

    public Orders findOrder(long orderId) {
        // TODO should business logic

        // TODO order 객체는 나중에 DB에서 조회 하는 것으로 변경 필요.
        return null;
    }

    // 주문 수정 메서드는 사용하지 않습니다.

    public List<Orders> findOrders() {
        // TODO should business logic

        // TODO order 객체는 나중에 DB에서 조회하는 것으로 변경 필요.
        return null;
    }

    public void cancelOrder() {
        // TODO should business logic
    }
}
