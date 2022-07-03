package exalted.posset.order.dto;

import exalted.posset.order.Order;
import exalted.posset.order.dto.OrderCoffeeResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrderResponseDto {
    private long orderId;
    private long memberId;

    private List<OrderCoffeeResponseDto> orderCoffees;
    private Order.OrderStatus orderStatus;
    private LocalDateTime createdAt;

}
