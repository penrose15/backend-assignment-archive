package com.codestates.order.dto;

import com.codestates.member.entity.Member;
import com.codestates.order.entity.OrderCoffee;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderPostDto {

    private long memberId;

    private List<OrderCoffeeDto> orderCoffees;

    public Member getMember() {
        Member member = new Member();
        member.setMemberId(memberId);
        return member;
    }
}
