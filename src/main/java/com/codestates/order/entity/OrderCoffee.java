package com.codestates.order.entity;
//제 페어가 OrderCoffee 왜 만들었냐고 그것 때문에 코드 더 복잡해졌다고 저를 탓하더군요;; 아마 제 페어가
//수업시간에 협곡을 간 모양입니다.

import com.codestates.coffee.entity.Coffee;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class OrderCoffee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderCoffeeId;

    @Column(nullable = false)
    private int quantity;

    //coffee와 ManyToOne 매핑
    @ManyToOne
    @JoinColumn(name = "COFFEE_ID") //외래키 식별자 이름
    private Coffee coffee;

    public void addCoffee(Coffee coffee) {
        this.coffee = coffee;
    }
    //Order와 ManyToOne 매핑
    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public void addOrder(Order order) {
        this.order = order;
    }
    //Coffee와 Order 단방향 매핑 완료료

}
