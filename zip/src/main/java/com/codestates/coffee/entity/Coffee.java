package com.codestates.coffee.entity;

import com.codestates.order.entity.OrderCoffee;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Coffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long coffeeId;

    @Column(nullable = false, length = 100)
    private String korName;
    @Column(nullable = false, length = 100)
    private String engName;
    @Column(nullable = false)
    private int price;

    @Column(length = 3, nullable = false, unique = true)
    private String coffeeCode;

    @Enumerated(EnumType.STRING)
    private CoffeeStatus coffeeStatus = CoffeeStatus.COFFEE_EXIST;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "MODIFIED_AT")
    private LocalDateTime modifiedAt = LocalDateTime.now();

    @OneToMany(mappedBy = "coffee")
    private List<OrderCoffee> orderCoffees = new ArrayList<>();

    public void addOrderCoffee(OrderCoffee orderCoffee) {
        orderCoffees.add(orderCoffee);
    }

    @Getter
    public enum CoffeeStatus{
        COFFEE_EXIST("판매 중"),
        COFFEE_SOLD_OUT("품절");
        private String description;

        CoffeeStatus(String description) {
            this.description = description;
        }
    }

}
