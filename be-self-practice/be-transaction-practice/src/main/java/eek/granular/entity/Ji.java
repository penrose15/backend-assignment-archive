package eek.granular.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@SequenceGenerator(name = "Ji_SEQ_GENERATOR",
initialValue = 1,
allocationSize = 50)//시퀀스 한번 호출시 증가하는 수(성능 최적화시 사용된다)
public class Ji {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "Ji_SEQ_GENERATOR")
    private Long jiId;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, name = "CREATED_AT")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Transient//테이블 컬럼과 매핑하지 않음
    private int age;

    public Ji(String name) {
        this.name = name;
    }
}
