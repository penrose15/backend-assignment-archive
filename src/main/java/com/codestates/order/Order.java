package com.codestates.order;

import com.codestates.coffee.entity.CoffeeRef;
import com.codestates.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// TODO V10
@Getter
@Setter
@Table("ORDERS")
public class Order {
    @Id
    private long orderId;

    // Member 객체 참조 대신에 memberId를 참조하도록 한다.
    private AggregateReference<Member, Long> memberId;

    @MappedCollection(idColumn = "ORDER_ID")
    private Set<CoffeeRef> orderCoffees;

    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;
    private LocalDateTime createdAt;

    public Order() {
        this.orderCoffees = new HashSet<>();
    }

    public void setMemberId(AggregateReference<Member, Long> memberId) {
        this.memberId = memberId;
    }

    public void setOrderCoffees(Set<CoffeeRef> orderCoffees) {
        this.orderCoffees = orderCoffees;
    }

    public enum OrderStatus {
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 확정"),
        ORDER_COMPLETE(3, "주문 완료"),
        ORDER_CANCEL(4, "주문 취소");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        OrderStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }

}
