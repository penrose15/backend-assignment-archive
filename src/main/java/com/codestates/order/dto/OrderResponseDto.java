package com.codestates.order.dto;

import com.codestates.coffee.entity.CoffeeRef;
import com.codestates.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

// TODO V10
@Getter
@Setter
public class OrderResponseDto {
    private long orderId;
    private long memberId;
    private Set<OrderCoffeeDto> orderCoffees;
    private LocalDateTime createdAt;
}
