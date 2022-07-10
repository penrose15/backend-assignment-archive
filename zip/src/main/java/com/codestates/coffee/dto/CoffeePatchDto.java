package com.codestates.coffee.dto;

import com.codestates.coffee.entity.Coffee;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Getter
@Valid
public class CoffeePatchDto {
    private long coffeeId;

    @Pattern(regexp = "^[가-힣\\s]+$")
    private String korName;
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String engName;

    @Range(min =10, max=50000)
    private int price;

    private Coffee.CoffeeStatus coffeeStatus;

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }
}
