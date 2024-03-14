package com.codestates.coffee;

import com.codestates.NotSpace;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

public class CoffeePatchDTO {

    private long coffeeId;
    @NotSpace
    private String korName;
    @Pattern(regexp = "\\s?[a-zA-Z](\\s?[a-zA-Z])*\\s?$", message = "공백을 2개 연속으로 입력할 수 없습니다.")
    private String engName;
    @Range(min=100, max = 50000)
    private Integer price;

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getKorName() {
        return korName;
    }

    public void setKorName(String korName) {
        this.korName = korName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
