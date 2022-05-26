package com.codestates.order;

import com.codestates.order.dto.OrderPostDto;
import com.codestates.order.dto.OrderResponseDto;
import com.codestates.order.mapper.OrderMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v10/orders")
@Validated
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper mapper;

    public OrderController(OrderService orderService, OrderMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postOrder(@Valid @RequestBody OrderPostDto orderPostDto) {
        // TODO mapper가 List<OrderCoffee>의 데이터를 Order 멤버와 매핑할 수 있는 처리를 해줘야 됨.
        Orders order = orderService.createOrder(mapper.orderPostDtoToOrder(orderPostDto));
        return new ResponseEntity<>(mapper.orderToOrderResponseDto(order), HttpStatus.CREATED);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity getOrder(@PathVariable("order-id") @Positive long orderId) {
        Orders order = orderService.findOrder(orderId);

        return new ResponseEntity<>(mapper.orderToOrderResponseDto(order), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getOrders() {
        List<Orders> orders = orderService.findOrders();

        List<OrderResponseDto> response = orders.stream()
                                                .map(order -> mapper.orderToOrderResponseDto(order))
                                                .collect(Collectors.toList());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{order-id}")
    public void cancelOrder(@PathVariable("order-id") long orderId) {
        System.out.println("# cancel order");
        orderService.cancelOrder();
    }
}
