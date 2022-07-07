package com.codestates.order.mapper;

import com.codestates.coffee.entity.Coffee;
import com.codestates.coffee.service.CoffeeService;
import com.codestates.member.entity.Member;
import com.codestates.order.dto.*;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();
        Member member = new Member();
        order.setOrderId(order.getOrderId());
        member.setMemberId(orderPostDto.getMemberId());
        List<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffees().stream().map(orderCoffeeDto -> {
            OrderCoffee orderCoffee = new OrderCoffee();
            Coffee coffee = new Coffee();
            coffee.setCoffeeId(orderCoffeeDto.getCoffeeId());
            orderCoffee.addOrder(order);
            orderCoffee.addCoffee(coffee);
            orderCoffee.setQuantity(orderCoffeeDto.getQuantity());
            return orderCoffee;
        }).collect(Collectors.toList());
        order.setMember(member);
        order.setOrderCoffees(orderCoffees);
        return order;
    }

    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);

    default OrderResponseDto orderToOrderResponseDto(Order order, List<Coffee> coffees) {
        List<OrderCoffee> orderCoffees = order.getOrderCoffees();

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setMember(order.getMember());
        orderResponseDto.setOrderStatus(order.getOrderStatus());
        orderResponseDto.setCreatedAt(order.getCreatedAt());
        orderResponseDto.setOrderCoffees(orderCoffeesToOrderCoffeeResponseDtos(orderCoffees));
        return orderResponseDto;

    }
    default List<OrderCoffeeResponseDto> orderCoffeesToOrderCoffeeResponseDtos(
            List<OrderCoffee> orderCoffees) {
        return orderCoffees.stream()
                .map( c-> OrderCoffeeResponseDto.builder()
                        .coffeeId(c.getCoffee().getCoffeeId())
                        .quantity(c.getQuantity())
                        .price(c.getCoffee().getPrice())
                        .korName(c.getCoffee().getKorName())
                        .engName(c.getCoffee().getEngName())
                        .build()).collect(Collectors.toList());
    }

    default List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders, List<Coffee> coffees) {
        List<OrderResponseDto> list = orders.stream()
                .map(c -> orderToOrderResponseDto(c, coffees))
                .collect(Collectors.toList());
        return list;
    }

//
//
//}
}