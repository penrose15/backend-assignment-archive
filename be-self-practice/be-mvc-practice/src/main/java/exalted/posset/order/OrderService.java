package exalted.posset.order;

import exalted.posset.exception.BusinessLogicException;
import exalted.posset.exception.ExceptionCode;
import exalted.posset.coffee.CoffeeService;
import exalted.posset.member.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CoffeeService coffeeService;
    private final MemberService memberService;

    public OrderService(OrderRepository orderRepository, CoffeeService coffeeService, MemberService memberService) {
        this.orderRepository = orderRepository;
        this.coffeeService = coffeeService;
        this.memberService = memberService;
    }
    public Order verifyOrder(long orderId) {
        Optional<Order> findOrder = orderRepository.findById(orderId);
        Order order = findOrder.orElseThrow(() ->new BusinessLogicException(ExceptionCode.ORDER_NOT_EXIST));
        return order;
    }
    public void verifyOrderExist(long orderId) {
        Optional<Order> findOrder = orderRepository.findById(orderId);
        if(findOrder.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.ORDER_EXIST);
        }
    }

    public Order createOrder(Order order) {
        memberService.verifiedMemberById(order.getMemberId().getId());
        verifyOrderExist(order.getOrderId());
        order.getOrderCoffees().stream().forEach(c -> coffeeService.verifyById(c.getCoffeeId()));
        return orderRepository.save(order);
    }
//    public Order updateOrder(Order order) {
//        memberService.verifiedMemberById(order.getMemberId().getId());
//        order.getOrderCoffees().stream().forEach(c-> coffeeService.verifyById(c.getCoffeeId()));
//    }
    public Order findOrder(long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        Order order1 = order.orElseThrow(() ->new BusinessLogicException(ExceptionCode.ORDER_NOT_EXIST));
        return order1;
    }

    public List<Order> findOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public void cancelOrder(long orderId) {
        Order order1 = verifyOrder(orderId);
        if(order1.getOrderStatus().getStatus()>=2) {
            throw new BusinessLogicException(ExceptionCode.ORDER_CANNOT_CANCEL);
        }
        else {
            order1.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
            orderRepository.save(order1);
        }
    }
}
