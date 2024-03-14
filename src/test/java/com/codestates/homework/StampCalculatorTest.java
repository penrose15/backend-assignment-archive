package com.codestates.homework;

import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StampCalculatorTest {
    @Test
    @DisplayName("실습1: 스탬프 카운트 계산 단위 테스트")
    public void calculateStampCountTest() {
        // TODO 여기에 테스트 케이스를 작성해주세요.
        //given
        int nowCount = 5;
        int earned = 3;

        //when
        int actual = calculateStampCount(nowCount, earned);
        int expected = 7;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("실습1: 주문 후 누적 스탬프 카운트 계산 탄위 테스트")
    public void calculateEarnedStampCountTest(){
        // TODO 여기에 테스트 케이스를 작성해주세요.
        Order order = new Order();
        OrderCoffee orderCoffee = new OrderCoffee();
        orderCoffee.setQuantity(3);

        OrderCoffee orderCoffee1 = new OrderCoffee();
        orderCoffee1.setQuantity(5);

        order.setOrderCoffees(List.of(orderCoffee1,orderCoffee));

        //when
        int actual = calculateEarnedStampCount(order);
        int expected = 8;

        assertThat(actual).isEqualTo(expected);
    }

    public int calculateStampCount(int nowCount, int earned) {
        return nowCount + earned;
    }

    public int  calculateEarnedStampCount(Order order) {
        int result =  order.getOrderCoffees().stream()
                .mapToInt(oc -> oc.getQuantity())
                .sum();

        return result;
    }
}
