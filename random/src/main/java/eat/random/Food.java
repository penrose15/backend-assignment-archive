package eat.random;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(value =EnumType.STRING)
    private BLD bld;

    public enum BLD {
        BREAKFAST("BREAKFAST"),
        LUNCH("LUNCH"),
        DINNER("DINNER");

        @Getter
        private String status;

        BLD(String status) {
            this.status = status;
        }
    }

    public Food(String name, Food.BLD bld) {
        this.name = name;
        this.bld = bld;
    }
}
