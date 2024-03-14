package com.codestates.order.dto;

import com.codestates.member.entity.Member;
import lombok.Getter;

import java.util.List;

@Getter

public class OrderPatchDto {

    private long orderId;
    private long memberId;

    private List<OrderCoffeeDto> orderCoffees;

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
