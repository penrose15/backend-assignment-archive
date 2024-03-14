package eek.granular.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Jeun {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //기본키 생성을 데이터베이스에 위임하는 전략(null) insert Query시 그때 DB가 처리
    //(Mysql의 Auto_Increment 기능으로 사용)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    //데이터베이스에서 제공하는 시퀀스를 사용하여 기본키를 생성하는 전략
    //엔티티가 영속성 컨텍스트에 저장되기 전 데이터베이스에서 시퀀스값을 조회함
    //....Hibernate: call next value for ji_seq_generator
    //@Generation(strategy = GenerationType.TABLE)
    //키 생성 전용 Table을 별도로 만들어야하고 키 조회, 업데이트 쿼리를 추가적으로 전성해야 해서 좋은 선택은 아님
    private long jeunId;

    @Column(nullable = false)
    private String name;

    public Jeun(String name) {
        this.name = name;
    }
}
