package com.codestates.coffee.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

//TODO 필드 추가 됨
@Getter
@Setter
public class Coffee {
    @Id
    private long coffeeId;

    private String korName;
    private String engName;
    private int price;
    private String coffeeCode;
}
