package com.codestates.order;

import com.codestates.order.dto.OrderCoffeeDto;
import com.codestates.order.dto.OrderPatchDto;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-11T00:14:43+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto) {
        if ( orderPatchDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setMember( orderPatchDto.getMember() );
        order.setOrderId( orderPatchDto.getOrderId() );
        order.setOrderCoffees( orderCoffeeDtoListToOrderCoffeeList( orderPatchDto.getOrderCoffees() ) );

        return order;
    }

    protected OrderCoffee orderCoffeeDtoToOrderCoffee(OrderCoffeeDto orderCoffeeDto) {
        if ( orderCoffeeDto == null ) {
            return null;
        }

        OrderCoffee orderCoffee = new OrderCoffee();

        orderCoffee.setQuantity( orderCoffeeDto.getQuantity() );

        return orderCoffee;
    }

    protected List<OrderCoffee> orderCoffeeDtoListToOrderCoffeeList(List<OrderCoffeeDto> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderCoffee> list1 = new ArrayList<OrderCoffee>( list.size() );
        for ( OrderCoffeeDto orderCoffeeDto : list ) {
            list1.add( orderCoffeeDtoToOrderCoffee( orderCoffeeDto ) );
        }

        return list1;
    }
}
