package exalted.posset.coffee.dto;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class CoffeePostDto {
    @NotBlank
    private String korName;
    @NotBlank
    private String engName;
    @Range(min = 0, max = 100000)
    private int price;
    @NotBlank
    @Pattern(regexp = "^[A-Z]*$")
    private String coffeeCode;
}
