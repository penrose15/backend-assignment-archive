package exalted.posset.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCoffeeResponseDto {

    private long coffeeId;
    private String korName;
    private String engName;
    private int price;
    private int quantity;

}
