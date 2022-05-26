package com.codestates.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Positive;

@Getter
@Setter
public class OrderCoffeeDto {
    @Positive
    private long coffeeId;
}
