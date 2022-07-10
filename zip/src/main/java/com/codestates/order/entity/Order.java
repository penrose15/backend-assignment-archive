package com.codestates.order.entity;


import com.codestates.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ORDERS")
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
    }

    @OneToMany(mappedBy = "order")
    private List<OrderCoffee> orderCoffees = new ArrayList<>();

    public void addOrderCoffee(OrderCoffee orderCoffee) {
        this.orderCoffees = orderCoffees;
        if(orderCoffee.getOrder() != this) {
            orderCoffee.addOrder(this);
        }
    }

    public void addOrderCoffees(OrderCoffee orderCoffee) {
        orderCoffees.add(orderCoffee);
    }

    @Enumerated(EnumType.STRING)
    private OrderStatus orderstatus = OrderStatus.ORDER_REQUEST;

    @Getter
    public enum OrderStatus {
        ORDER_REQUEST(1,"order request"),
        ORDER_ACCEPT(2,"order accept"),
        ORDER_COMPLETE(3, "order complete"),
        ORDER_CANCELED(4, "order cancel");

        @Getter
        private int status;

        @Getter
        private String description;

        OrderStatus(int status, String description) {

            this.status = status;
            this.description = description;
        }
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public void setOrderstatus(OrderStatus orderstatus) {
        this.orderstatus = orderstatus;
    }
}
