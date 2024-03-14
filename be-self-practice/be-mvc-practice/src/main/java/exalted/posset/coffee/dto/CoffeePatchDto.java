package exalted.posset.coffee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class CoffeePatchDto {

    private long coffeeId;
    @NotBlank
    private String korName;
    @NotBlank
    @Pattern(regexp = "^[a-z|A-Z]*$", message = "공백x, 영어로만")
    private String engName;
    @NotBlank
    @Range(min = 0, max = 100000)
    private int price;

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }
}
