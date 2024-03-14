package exalted.posset.coffee;

import exalted.posset.coffee.dto.CoffeePatchDto;
import exalted.posset.coffee.dto.CoffeePostDto;
import exalted.posset.coffee.dto.CoffeeResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoffeeMapper {
    Coffee coffeePostToCoffee(CoffeePostDto coffeePostDto);
    Coffee coffeePatchToCoffee(CoffeePatchDto coffeePatchDto);
    CoffeeResponseDto coffeeToCoffeeResponseDto(Coffee coffee);

    List<CoffeeResponseDto> coffeeToCoffeeResponseDtos(List<Coffee> coffees);
}
