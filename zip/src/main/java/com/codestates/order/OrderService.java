package com.codestates.order;

import com.codestates.bussinessLogicException.BusinessLogicException;
import com.codestates.bussinessLogicException.ExceptionCode;
import com.codestates.coffee.entity.Coffee;
import com.codestates.coffee.service.CoffeeService;
import com.codestates.member.entity.Member;
import com.codestates.member.entity.Stamp;
import com.codestates.member.service.MemberService;
import com.codestates.order.entity.Order;
import com.codestates.order.entity.OrderCoffee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberService memberService;
    private final CoffeeService coffeeService;

    public OrderService(OrderRepository orderRepository, MemberService memberService, CoffeeService coffeeService) {
        this.orderRepository = orderRepository;
        this.memberService = memberService;
        this.coffeeService = coffeeService;
    }

    public Order findVerifiedOrder(long orderId) {
        Optional<Order> findOrder = orderRepository.findById(orderId);
        Order order = findOrder.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return order;
    }

    public Order createOrder(Order order) {
        memberService.findMemberById(order.getMember().getMemberId());
        coffeeService.verifiedCoffee(order);
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        Order findOrder = findVerifiedOrder(order.getOrderId());
        if(findOrder.getOrderstatus().getStatus()>=2) {
            throw new BusinessLogicException(ExceptionCode.ORDER_ALREADY_ACCEPT);
        }
        else {
            Optional.ofNullable(order.getOrderstatus())
                    .ifPresent(os -> findOrder.setOrderstatus(os));
            findOrder.setModifiedAt(LocalDateTime.now());
        }
        return orderRepository.save(findOrder);
    }
    public Order findOrder1(long orderId) {
        return findVerifiedOrder(orderId);
    }
    public Page<Order> findOrders(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size, Sort.by("orderId").descending()));

    }
    public void cancelOrder(long orderId) {
        Order order = findVerifiedOrder(orderId);
        int step = order.getOrderstatus().getStatus();
        if(step >=2) {
            throw new BusinessLogicException(ExceptionCode.ORDER_ALREADY_ACCEPT);
        }
        else {
            order.setOrderstatus(Order.OrderStatus.ORDER_CANCELED);
            order.setModifiedAt(LocalDateTime.now());
            orderRepository.save(order);
        }
    }

    public int stampCount(Order order) {
        Member member  = memberService.findMemberById(order.getMember().getMemberId());
        Stamp stamp = member.getStamp();
        Integer quantities =
        order.getOrderCoffees().stream().map(o -> o.getQuantity()).mapToInt(o->o).sum();


        stamp.setStampCount(stamp.getStampCount() + quantities);
        memberService.updateMember(member);
        return stamp.getStampCount();
    }

}
