package com.codestates.order.mapper;

import com.codestates.coffee.entity.CoffeeRef;
import com.codestates.order.Orders;
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
    default Orders orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Orders orders = new Orders();
        orders.setMemberId(new AggregateReference.IdOnlyAggregateReference(orderPostDto.getMemberId()));
        Set<CoffeeRef> orderCoffees = orderPostDto.getOrderCoffees()
                .stream()
                .map(orderCoffeeDto -> new CoffeeRef(orderCoffeeDto.getCoffeeId(),
                        orderCoffeeDto.getQuantity()))
                .collect(Collectors.toSet());
        orders.setOrderCoffees(orderCoffees);
        orders.setCreatedAt(LocalDateTime.now());
        return orders;
    }

    default OrderResponseDto orderToOrderResponseDto(Orders order) {
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

        return orderResponseDto;
    }
}
