package com.codestates.order.entity;


import com.codestates.coffee.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

//order과 coffee는 다대다 관계이므로 일대다의 관계를 만들기 위해 조인테이블을 하나 만든다.
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCoffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderCoffeeId;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public void addOrder(Order order) {
        this.order = order;
        if(!order.getOrderCoffees().contains(this)) {
            order.addOrderCoffees(this);
        }
    }

    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Coffee coffee;

    public void addCoffee(Coffee coffee) {
        this.coffee = coffee;
        if(!coffee.getOrderCoffees().contains(this)){
            coffee.addOrderCoffee(this);
        }
    }
}
