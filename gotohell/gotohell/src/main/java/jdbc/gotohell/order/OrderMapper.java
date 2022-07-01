package jdbc.gotohell.order;

import jdbc.gotohell.coffee.Coffee;
import jdbc.gotohell.coffee.CoffeeService;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface OrderMapper {
    default Order orderPostDtoToOrder(OrderPostDto orderPostDto) {
        Order order = new Order();
        order.setMemberId(
                new AggregateReference.IdOnlyAggregateReference<>(orderPostDto.getMemberId()));
        Set<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffees()
                .stream()
                .map(orderCoffeeDto -> new OrderCoffee(orderCoffeeDto.getCoffeeId(), orderCoffeeDto.getQuantity()))
                .collect(Collectors.toSet());
        order.setOrderCoffee(orderCoffees);
        return order;
    }
    default OrderResponseDto orderToOrderResponseDto(CoffeeService coffeeService, Order order) {
        long memberId = order.getMemberId().getId();
        List<OrderCoffeeResponseDto> orderCoffees = orderToOrderCoffeeResponseDto(coffeeService, order.getOrderCoffee());

        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderCoffees(orderCoffees);
        orderResponseDto.setMemberId(memberId);
        orderResponseDto.setLocalDateTime(order.getCreateAt());
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setOrderStatus(order.getOrderStatus());

        return orderResponseDto;
    }
    default List<OrderCoffeeResponseDto> orderToOrderCoffeeResponseDto(CoffeeService coffeeService, Set<OrderCoffee> orderCoffees) {
        return orderCoffees.stream()
                .map(c -> {
                    Coffee coffee = coffeeService.findCoffee(c.getCoffeeId());
                    return new OrderCoffeeResponseDto(coffee.getCoffeeId(),
                            coffee.getKorName(),
                            coffee.getEngName(),
                            coffee.getPrice(),
                            c.getQuantity());
                }).collect(Collectors.toList());
    }
}
