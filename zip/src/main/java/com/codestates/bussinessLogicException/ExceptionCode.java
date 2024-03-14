package com.codestates.bussinessLogicException;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    MEMBER_ALREADY_EXIST(400, "member already exist"),
    MEMBER_NOT_FOUND(404, "member not found"),
    COFFEE_ALREADY_EXIST(400, "coffee already exist"),
    COFFEE_NOT_FOUND(404, "coffee not found"),
    ORDER_NOT_FOUND(404, "order not found"),
    ORDER_ALREADY_ACCEPT(400, "order already accept");
    //추가 예정

    private int status;

    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
