package exalted.posset.order;

import exalted.posset.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Table(name ="ORDERS")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    private long orderId;

    private AggregateReference<Member, Long> memberId;

    @MappedCollection(idColumn = "ORDER_ID")
    private Set<OrderCoffee> orderCoffees = new LinkedHashSet<>();
    private OrderStatus orderStatus = OrderStatus.ORDER_REQUEST;
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum OrderStatus{
        ORDER_REQUEST(1, "request"),
        ORDER_ACCEPT(2, "accept"),
        ORDER_COMPLETE(3, "complete"),
        ORDER_CANCEL(4, "cancel");
        @Getter
        @Setter
        private int status;
        @Getter
        private String description;

        OrderStatus(int status, String description) {
            this.status = status;
            this.description = description;
        }
    }
}
