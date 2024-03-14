package com.codestates.order.mapper;

import com.codestates.order.dto.OrderPatchDto;
import com.codestates.order.dto.OrderResponseDto;
import com.codestates.order.entity.Order;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-13T20:28:27+0900",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order orderPatchDtoToOrder(OrderPatchDto orderPatchDto) {
        if ( orderPatchDto == null ) {
            return null;
        }

        Order order = new Order();

        order.setOrderId( orderPatchDto.getOrderId() );
        order.setOrderStatus( orderPatchDto.getOrderStatus() );

        return order;
    }

    @Override
    public List<OrderResponseDto> ordersToOrderResponseDtos(List<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<OrderResponseDto> list = new ArrayList<OrderResponseDto>( orders.size() );
        for ( Order order : orders ) {
            list.add( orderToOrderResponseDto( order ) );
        }

        return list;
    }
}