package exalted.posset.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCoffeeDto {
    @Positive
    private long coffeeId;
    @Positive
    private int quantity;
}
