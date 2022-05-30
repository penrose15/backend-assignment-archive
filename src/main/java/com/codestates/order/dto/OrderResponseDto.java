package com.codestates.order.dto;

import com.codestates.order.Order;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.Set;


// TODO V10
@Getter
@Setter
public class OrderResponseDto {
    private long orderId;
    private long memberId;
    private Order.OrderStatus orderStatus;
    private Set<OrderCoffeeDto> orderCoffees;
    private LocalDateTime createdAt;
}
