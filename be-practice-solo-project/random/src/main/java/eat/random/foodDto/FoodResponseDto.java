package eat.random.foodDto;

import eat.random.Food;
import lombok.*;

@Getter
@Setter
public class FoodResponseDto {
    private long id;

    private String name;

    private Food.BLD bld;
}
