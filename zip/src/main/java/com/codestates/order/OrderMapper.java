package com.codestates.order;

import com.codestates.coffee.service.CoffeeService;
import com.codestates.order.dto.*;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto, CoffeeService coffeeService) {
        Order order = new Order();
        order.setModifiedAt(LocalDateTime.now());
        order.setCreatedAt(LocalDateTime.now());

        List<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffees().stream()
                .map(o-> {
                    OrderCoffee orderCoffee = new OrderCoffee();
                    //orderCoffee.setOrderCoffeeId(order.getOrderId());
                    orderCoffee.addCoffee(coffeeService.findCoffee(o.getCoffeeId()));
                    orderCoffee.addOrder(order);
                    orderCoffee.setQuantity(o.getQuantity());
                    return orderCoffee;
                }).collect(Collectors.toList());
        order.setMember(orderPostDto.getMember());
        order.setOrderCoffees(orderCoffees);
        return order;
    }
    Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto);

//    default Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto, CoffeeService coffeeService, OrderService orderService) {
//        Order order = orderService.findOrder1(orderPatchDto.getOrderId());
//
//        System.out.println("#patch - setOrderId");
//        order.setModifiedAt(LocalDateTime.now());
//        order.setCreatedAt(LocalDateTime.now());
//
//        List<OrderCoffee> orderCoffees = order.getOrderCoffees();
//
//
////        List<OrderCoffee> orderCoffees = orderPatchDto.getOrderCoffees().stream()
////                .map(o-> {
////                    OrderCoffee orderCoffee = new OrderCoffee();
////                    System.out.println("#patch - addCoffee");
////                    orderCoffee.addCoffee(coffeeService.findCoffee(o.getCoffeeId()));
////                    orderCoffee.addOrder(order);
////                    orderCoffee.setQuantity(o.getQuantity());
////                    return orderCoffee;
////                }).collect(Collectors.toList());
//        order.setMember(orderPatchDto.getMember());
//        for(OrderCoffee o : orderCoffees) {
//            order.addOrderCoffees(o);
//        }
//        return order;
//    }

    default OrderResponseDto orderToOrderResponseDto(Order order, OrderService orderService) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setOrderStatus(order.getOrderstatus());
        orderResponseDto.setMember(order.getMember());
        orderResponseDto.setOrderCoffeeResponseDtos(lists(order));
        orderResponseDto.setCreatedAt(order.getCreatedAt());
//        orderResponseDto.setStampCount(orderService.stampCount(order));//stampcount 추가
        return orderResponseDto;
    }

    default List<OrderResponseDto> orderToOrderResponseDtos(List<Order> orders, OrderService orderService) {
        List<OrderResponseDto> list = orders.stream().map(o -> orderToOrderResponseDto(o, orderService))
                .collect(Collectors.toList());
        return list;
    }

    default List<OrderCoffeeResponseDto> lists(Order order) {
        return order.getOrderCoffees().stream()
                .map(o ->
                    OrderCoffeeResponseDto.builder().
                            coffeeId(o.getCoffee().getCoffeeId())
                            .korName(o.getCoffee().getKorName())
                            .engName(o.getCoffee().getEngName())
                            .price(o.getCoffee().getPrice())
                            .coffeeCode(o.getCoffee().getCoffeeCode())
                            .quantity(o.getQuantity())
                            .build()
                    ).collect(Collectors.toList());

    }

//    default OrderResponseDto.stampResponseDtos stampCount(Order order, OrderService orderService) {
//        StampResponseDto stampResponseDto = new StampResponseDto();
//        long memberId = order.getMember().getMemberId();
//        int stampCount = orderService.stampCount(order);
//
//        stampResponseDto.setMemberId(memberId);
//        stampResponseDto.setStampCount(stampCount);
//        return OrderResponseDto.stampResponseDtos;
//    }


}
