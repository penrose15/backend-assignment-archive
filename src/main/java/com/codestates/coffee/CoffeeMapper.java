package com.codestates.coffee;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePatchDtoToCoffee(CoffeePatchDto coffeePatchDto);
    Coffee coffeePostDtoToCoffee(CoffeePostDto coffeePostDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);
}
