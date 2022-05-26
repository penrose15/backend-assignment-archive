package com.codestates.order;

import com.codestates.coffee.entity.CoffeeRef;
import com.codestates.member.entity.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// TODO V10
@Getter
@Setter
public class Orders {
    @Id
    private long orderId;

    // Member 객체 참조 대신에 memberId를 참조하도록 한다.
    private AggregateReference<Member, Long> memberId;

    @MappedCollection(idColumn = "ORDER_ID")
    private Set<CoffeeRef> orderCoffees;

    private LocalDateTime createdAt;

    public Orders() {
        this.orderCoffees = new HashSet<>();
    }

    public void setMemberId(AggregateReference<Member, Long> memberId) {
        this.memberId = memberId;
    }

    public void setOrderCoffees(Set<CoffeeRef> orderCoffees) {
        this.orderCoffees = orderCoffees;
    }
}
