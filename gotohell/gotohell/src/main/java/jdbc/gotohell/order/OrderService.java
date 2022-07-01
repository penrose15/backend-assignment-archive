package jdbc.gotohell.order;

import jdbc.gotohell.coffee.CoffeeService;
import jdbc.gotohell.exception.BusinessLogicException;
import jdbc.gotohell.exception.ExceptionCode;
import jdbc.gotohell.member.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
        Optional<Order> order = orderRepository.findById(orderId);
        Order findOrder = order.orElseThrow(() -> new BusinessLogicException(ExceptionCode.ORDER_NOT_FOUND));
        return findOrder;
    }

    public Order createOrder(Order order) {
        memberService.findVerifyMember(order.getMemberId().getId());
        order.getOrderCoffee().stream()
                .forEach(orderCoffee -> {
                    coffeeService.findVerifiedCoffee(orderCoffee.getCoffeeId());
                });

        return orderRepository.save(order);
    }
    public Order findOrder(long orderId) {
        return findVerifiedOrder(orderId);
    }
    public List<Order> findOrders() {
        return (List<Order>) orderRepository.findAll();
    }
    public void cancelOrder(long orderId) {
        Order findOrder = findVerifiedOrder(orderId);
        if(findOrder.getOrderStatus().getStepNumber()>=2) {
            new BusinessLogicException(ExceptionCode.CANNOT_CHANGE_ORDER);
        }
        else {
            findOrder.setOrderStatus(Order.OrderStatus.ORDER_CANCEL);
            orderRepository.save(findOrder);
        }

    }


}
