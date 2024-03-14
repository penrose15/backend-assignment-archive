package exalted.posset.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@Table(name = "ORDER_COFFEE")
@AllArgsConstructor
@NoArgsConstructor
public class OrderCoffee {
    private long coffeeId;

    private int quantity;

}
