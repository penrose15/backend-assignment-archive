package com.codestates.coffee;

import com.codestates.NotSpace;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

public class CoffeePostDTO {
    @NotSpace
    private String korName;
    @OneBlank
    private String engName;
    @Range(min =100, max = 50000)
    private Integer price;

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
