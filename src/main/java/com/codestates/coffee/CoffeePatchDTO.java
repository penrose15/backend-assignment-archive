package com.codestates.coffee;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

public class CoffeePatchDTO {

    @Pattern(regexp = "^(?=\\s*\\S).*$" , message = "커피 이름은 공백이 아니어야 합니다.")
    private String korName;

    @Pattern(regexp = "^(?=\\s*\\S).*$" , message = "커피 이름은 공백이 아니어야 합니다.")
    @Pattern(regexp = "\\s?[a-zA-Z](\\s?[a-zA-Z])*\\s?$", message = "공백을 2개 연속으로 입력할 수 없습니다.")
    //@OneSpace
    private String engName;
    @Range(min = 100, max = 50000)
    private int price;
    //@Min(1)
    private long coffeeId;

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(long coffeeId) {

        this.coffeeId = coffeeId;
    }
}
