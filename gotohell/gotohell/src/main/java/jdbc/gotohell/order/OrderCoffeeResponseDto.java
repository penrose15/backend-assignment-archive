package jdbc.gotohell.order;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OrderCoffeeResponseDto {
    private long coffeeId;
    private String korName;
    private String engName;
    private int price;
    private int quantity;
}
