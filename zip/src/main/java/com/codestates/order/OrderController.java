package com.codestates.order;


import com.codestates.coffee.service.CoffeeService;
import com.codestates.order.dto.OrderPatchDto;
import com.codestates.order.dto.OrderPostDto;
import com.codestates.order.entity.Order;
import com.codestates.response.multiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v11/orders")
@Validated
public class OrderController {
    private final OrderService orderService;
    private final CoffeeService coffeeService;
    private final OrderMapper mapper;

    public OrderController(OrderService orderService, CoffeeService coffeeService, OrderMapper mapper) {
        this.orderService = orderService;
        this.coffeeService = coffeeService;
        this.mapper = mapper;
    }
    @PostMapping
    public ResponseEntity createOrder(@RequestBody @Valid OrderPostDto orderPostDto) {
        Order order = mapper.orderPostDtoToOrder(orderPostDto, coffeeService);
        Order result = orderService.createOrder(order);
        //orderService.stampCount(order);
        //StampReponseDto = mapper.stampCount(order, orderService);
        stamps(result, orderService);

        return new ResponseEntity<>(mapper.orderToOrderResponseDto(result, orderService), HttpStatus.CREATED);
    }

    public void stamps(Order order, OrderService orderService) {
        orderService.stampCount(order);
    }

    @PatchMapping("/{order-id}")//포기 ㅠㅠ
    public ResponseEntity updateOrder(@PathVariable("order-id")long orderId,
                                      @RequestBody @Valid OrderPatchDto orderPatchDto) {
        orderPatchDto.setOrderId(orderId);
        Order order = mapper.orderPatchDtoToOrder(orderPatchDto);
        Order result = orderService.updateOrder(order);

        return new ResponseEntity<>(mapper.orderToOrderResponseDto(result,orderService), HttpStatus.OK);

    }
    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") long orderId) {
        Order order = orderService.findOrder1(orderId);
        return new ResponseEntity<>(mapper.orderToOrderResponseDto(order, orderService), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders(@RequestParam int page,
                                    @RequestParam int size) {
        Page<Order> pageOrder = orderService.findOrders(page-1, size);
        List<Order> list = orderService.findOrders(page, size).getContent();
        return new ResponseEntity<>(
                new multiResponseDto<>(mapper.orderToOrderResponseDtos(list,orderService), pageOrder)
                , HttpStatus.OK);

        //return new ResponseEntity<>(mapper.orderToOrderResponseDtos(list,orderService), HttpStatus.OK);
    }
    @DeleteMapping("/{order-id}")
    public ResponseEntity deleteOrders(@PathVariable("order-id")long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}


