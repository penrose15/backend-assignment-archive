package exalted.posset.order;

import exalted.posset.coffee.CoffeeService;
import exalted.posset.order.dto.OrderCoffeeResponseDto;
import exalted.posset.order.dto.OrderPostDto;
import exalted.posset.order.dto.OrderResponseDto;
import exalted.posset.order.mapper.OrderMapper;
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
    private OrderService orderService;
    private CoffeeService coffeeService;
    private OrderMapper mapper;

    public OrderController(OrderService orderService, CoffeeService coffeeService, OrderMapper mapper) {
        this.orderService = orderService;
        this.coffeeService = coffeeService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody @Valid OrderPostDto orderPostDto) {
        Order order = orderService.createOrder(mapper.orderPostDtoToDto(orderPostDto));
        return new ResponseEntity<>(mapper.orderToOrderResponseDto(coffeeService, order), HttpStatus.CREATED);

    }

    @GetMapping("/{order-id}")
    public ResponseEntity findOrder(@PathVariable("order-id") @Positive long orderId) {
        Order order = orderService.findOrder(orderId);
        return new  ResponseEntity<>(mapper.orderToOrderResponseDto(coffeeService, order), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity findOrders() {
        List<Order> list = orderService.findOrders();

        List<OrderResponseDto> orderResponseDtoList = list.stream()
                .map(c -> {
                    OrderResponseDto orderResponseDto =  mapper.orderToOrderResponseDto(coffeeService,c);
                    return orderResponseDto;})
                .collect(Collectors.toList());
        return new ResponseEntity<>(orderResponseDtoList,HttpStatus.OK);
    }
    @DeleteMapping("/{order-id}")
    public ResponseEntity deleteOrder(@PathVariable("order-id") @Positive long orderId) {
        orderService.cancelOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
