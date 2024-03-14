package aop.practice.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Setter
@Getter
public class User {
    private Long id;
    private String name;


    public User(Long id, String name) {
        this.id = id;
        this.name = name;

    }

}
