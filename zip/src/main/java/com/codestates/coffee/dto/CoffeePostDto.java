package com.codestates.coffee.dto;

import com.codestates.coffee.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class CoffeePostDto {

    @NotBlank
    private String korName;

    @Pattern(regexp = "^([A-Za-z])(\\s?[A-Za-z])*$")
    private String engName;

    @Range(min = 10, max = 50000)
    private  int price;

    @NotBlank
    @Pattern(regexp = "^([A-Za-z]){3}$")
    private String coffeeCode;
}
