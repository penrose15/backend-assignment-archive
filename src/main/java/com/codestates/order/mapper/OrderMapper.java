package com.codestates.order.mapper;

import com.codestates.coffee.entity.CoffeeRef;
import com.codestates.order.Order;
import com.codestates.order.dto.OrderCoffeeDto;
import com.codestates.order.dto.OrderPostDto;
import com.codestates.order.dto.OrderResponseDto;
import org.mapstruct.Mapper;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

// TODO V10
@Mapper(componentModel = "spring")
public interface OrderMapper {
    // TODO 수정되었음.
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();
        order.setMemberId(new AggregateReference.IdOnlyAggregateReference(orderPostDto.getMemberId()));
        Set<CoffeeRef> orderCoffees = orderPostDto.getOrderCoffees()
                .stream()
                .map(orderCoffeeDto -> new CoffeeRef(orderCoffeeDto.getCoffeeId(),
                        orderCoffeeDto.getQuantity()))
                .collect(Collectors.toSet());
        order.setOrderCoffees(orderCoffees);
        order.setCreatedAt(LocalDateTime.now());
        return order;
    }

    default OrderResponseDto orderToOrderResponseDto(Order order) {
        Set<OrderCoffeeDto> orderCoffees = order.getOrderCoffees()
                .stream()
                .map(coffeeRef -> new OrderCoffeeDto(coffeeRef.getCoffeeId(),
                        coffeeRef.getQuantity()))
                .collect(Collectors.toSet());
        long memberId = order.getMemberId().getId();

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderCoffees(orderCoffees);
        orderResponseDto.setMemberId(memberId);
        orderResponseDto.setCreatedAt(order.getCreatedAt());
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setOrderStatus(order.getOrderStatus());

        return orderResponseDto;
    }
}
