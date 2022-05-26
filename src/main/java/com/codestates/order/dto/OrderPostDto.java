package com.codestates.order.dto;

import com.codestates.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.Set;

@Getter
@Setter
public class OrderPostDto {
    @Positive
    private long memberId;

    @Valid
    private Set<OrderCoffeeDto> orderCoffees;

    // Orders는 AggregateReference<Member, Long> 타입이므로 get 할 때 바꿔줘야 됨.
    public AggregateReference<Member, Long> getMemberId() {
        return new AggregateReference.IdOnlyAggregateReference(memberId);
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
