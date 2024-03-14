package a.b;

import lombok.Getter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Getter
public class ExPostDto {
    @NotBlank(message = "공백이면 안됩니다.")
    private String item;

    @Range(min = 100, max = 10000,message = "100에서 10000사이의 금액이어야 합니다.")
    private int price;
}
