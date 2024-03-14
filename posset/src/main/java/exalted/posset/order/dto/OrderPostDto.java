package exalted.posset.order.dto;


import exalted.posset.order.dto.OrderCoffeeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@NoArgsConstructor
public class OrderPostDto {
    @Positive
    private long memberId;

    @Valid
    private List<OrderCoffeeDto> orderCoffees;
}
