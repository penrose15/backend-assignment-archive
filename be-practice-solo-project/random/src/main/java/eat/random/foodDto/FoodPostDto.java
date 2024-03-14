package eat.random.foodDto;

import eat.random.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Valid
public class FoodPostDto {

    //private long id;

    @NotBlank
    @Pattern(regexp = "^[가-힣]+$")
    private String name;

    private Food.BLD bld;
}
