package com.jwt.model;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String password;

    private String role;

    public List<String> roles() {
        if(role.length() > 0) {
            return Arrays.asList(role.split(","));
        }
        return new ArrayList<>();
    }

}
