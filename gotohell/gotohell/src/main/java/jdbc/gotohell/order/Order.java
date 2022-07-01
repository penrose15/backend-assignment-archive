package jdbc.gotohell.order;

import jdbc.gotohell.member.Member;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Table("ORDERS")
public class Order {
    @Id
    private long orderId;

    private AggregateReference<Member, Long> memberId;
    //다른 애그리거트 루트 대 애그리거트 루트간의 객체 참조는 ID로 참조된다.
    //Member : Order = 1 : N 관계

    @MappedCollection(idColumn = "ORDER_ID")
    private Set<OrderCoffee> orderCoffee = new LinkedHashSet<>();
    //동일한 애그리거트 내에서는 객체 참조를 한다.
    //Order : Coffee의 관계는 N : N 관계이다.
    //그래서 OrderCoffee라는 1 : N관계로 풀어줄 중간 엔티티를 하나 만듬
    //근데 얘는 Order 클래스와 같은 애그리거트에 있음 (Order : OrderCoffee = 1 : N)
    //그래서 객체 참조를 함

    //@MappedCollection은 엔티티 클래스 간의 연관관계를 맺어주는 정보를 의미함
    //OrderCoffee테이블은 ORDER 테이블에서 기본키로 가지는 ORDER_ID를 외래키로 가짐

    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;
    //질문! 왜 ORDER_REQUEST로 설정 했는가? 다른 방법으로 디폴트를 설정하는 방법이 있나요??
    private LocalDateTime CreateAt = LocalDateTime.now();

    public enum OrderStatus {
        ORDER_REQUEST(1, "주문 요청"),
        ORDER_CONFIRM(2, "주문 확정"),
        ORDER_COMPLETE(3, "주문 완료"),
        ORDER_CANCEL(4, "주문 취소");

        @Getter
        private int stepNumber;

        @Getter
        private String stepDescription;

        OrderStatus(int stepNumber, String stepDescription) {
            this.stepNumber = stepNumber;
            this.stepDescription = stepDescription;
        }
    }
}
