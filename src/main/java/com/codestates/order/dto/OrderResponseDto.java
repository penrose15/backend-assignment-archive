package com.codestates.order.dto;

import com.codestates.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class OrderResponseDto {
    private long orderId;
    private AggregateReference<Member, Long> memberId;
    private LocalDateTime createdAt;
    // response로 던져줄 땐 memberId를 던져줘야 되므로 AggregateReference에서 memberId를 꺼낸다.
    public long getMemberId() {
        return memberId.getId();
    }
}
