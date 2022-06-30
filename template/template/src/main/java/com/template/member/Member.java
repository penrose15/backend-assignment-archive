package com.template.member;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id // Member 엔티티에 고유 식별자 역할을 한다.
    private long memberId;

    private String name;

    private String email;
}
