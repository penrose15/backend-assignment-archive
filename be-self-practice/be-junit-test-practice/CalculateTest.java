package com.codestates;

import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CalculateTest {

    @Test
    public void calculateStampCountTest() {
        //given
        int nowCount = 5;
        int earned = 3;

        //when
        int actual = calculateStampCount(5, 3);
        int expected = 7;

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void calculateEarnedStampCountTest() {
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
