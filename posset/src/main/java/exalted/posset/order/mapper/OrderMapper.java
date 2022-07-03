package exalted.posset.order.mapper;

import exalted.posset.coffee.Coffee;
import exalted.posset.coffee.CoffeeService;
import exalted.posset.order.Order;
import exalted.posset.order.OrderCoffee;
import exalted.posset.order.dto.OrderCoffeeResponseDto;
import exalted.posset.order.dto.OrderPostDto;
import exalted.posset.order.dto.OrderResponseDto;
import org.mapstruct.Mapper;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {

   default Order orderPostDtoToDto(OrderPostDto orderPostDto) {
       Order order = new Order();
       order.setMemberId(new AggregateReference.IdOnlyAggregateReference<>(orderPostDto.getMemberId()));
       Set<OrderCoffee> orderCoffees = orderPostDto.getOrderCoffees()
               .stream().map(c -> new OrderCoffee(c.getCoffeeId(),c.getQuantity())).collect(Collectors.toSet());
       order.setOrderStatus(order.getOrderStatus());
       order.setCreatedAt(order.getCreatedAt());
       order.setOrderCoffees(orderCoffees);
       return order;

   }
   default OrderResponseDto orderToOrderResponseDto(CoffeeService coffeeService, Order order) {
       long memberId = order.getMemberId().getId();
       List<OrderCoffeeResponseDto> list =
               orderCoffeeToOrderCoffeeResponseDto(coffeeService, order.getOrderCoffees());

       OrderResponseDto orderResponseDto = new OrderResponseDto();
       orderResponseDto.setOrderId(order.getOrderId());
       orderResponseDto.setMemberId(memberId);
       orderResponseDto.setOrderStatus(order.getOrderStatus());
       orderResponseDto.setOrderCoffees(list);

       orderResponseDto.setCreatedAt(order.getCreatedAt());

       return orderResponseDto;
   }

   default List<OrderCoffeeResponseDto> orderCoffeeToOrderCoffeeResponseDto(CoffeeService coffeeService,
                                                                            Set<OrderCoffee> set) {
       return set.stream()
               .map(c -> {
                   Coffee coffee = coffeeService.getCoffee(c.getCoffeeId());
                   return new OrderCoffeeResponseDto(c.getCoffeeId(),
                           coffee.getKorName(),
                           coffee.getEngName(),
                           coffee.getPrice(),
                           c.getQuantity());})
               .collect(Collectors.toList());

   }
}
