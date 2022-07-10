package com.codestates.order.dto;

import com.codestates.member.entity.Member;
import com.codestates.order.entity.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private long orderId;
    private long memberId;
    private List<OrderCoffeeResponseDto> orderCoffeeResponseDtos;
    private Order.OrderStatus orderStatus;
    private LocalDateTime createdAt;

//    private int stampCount;

    public void setMember(Member member) {
        this.memberId = member.getMemberId();
    }




}
