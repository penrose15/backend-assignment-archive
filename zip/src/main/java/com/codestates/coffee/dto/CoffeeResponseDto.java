package com.codestates.coffee.dto;

import com.codestates.coffee.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class CoffeeResponseDto {
    private long coffeeId;

    private String korName;

    private String engName;

    private int price;

    private Coffee.CoffeeStatus coffeeStatus;

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getCoffeeStatus() {
        return coffeeStatus.getDescription();
    }
}
