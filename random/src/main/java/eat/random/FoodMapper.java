package eat.random;

import eat.random.foodDto.FoodPatchDto;
import eat.random.foodDto.FoodPostDto;
import eat.random.foodDto.FoodResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FoodMapper {
    Food foodPostDtoToFood(FoodPostDto foodPostDto);

    Food foodPatchDtoToFood(FoodPatchDto foodPatchDto);

    FoodResponseDto foodToFoodResponseDto(Food food);

    List<FoodResponseDto> foodToFoodResponseDtos(List<Food> foods);
}
