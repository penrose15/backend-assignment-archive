package eat.random.foodDto;

import eat.random.Food;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
public class FoodPatchDto {

    private long id;

    @NotBlank
    @Pattern(regexp = "^[가-힣]+$")
    private String name;

    private Food.BLD bld;

    public void setId(long id) {
        this.id = id;
    }
}
