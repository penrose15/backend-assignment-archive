package com.codestates.order;

import com.codestates.coffee.entity.Coffee;
import com.codestates.coffee.entity.CoffeeRef;
import com.codestates.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class Orders {
    @Id
    private long orderId;

    // Member 객체 참조 대신에 memberId를 참조하도록 한다.
    private AggregateReference<Member, Long> memberId;
    private Set<CoffeeRef> orderCoffees;
    private LocalDateTime createdAt;

    public Orders() {
        this.orderCoffees = new HashSet<>();
    }

    public void addCoffee(Coffee coffee) {
        orderCoffees.add(new CoffeeRef(coffee.getCoffeeId()));
    }
}
