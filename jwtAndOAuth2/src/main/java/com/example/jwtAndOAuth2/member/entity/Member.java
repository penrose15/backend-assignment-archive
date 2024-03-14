package com.example.jwtAndOAuth2.member.entity;

import com.example.jwtAndOAuth2.baseEntity.BaseEntity;
import lombok.*;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String profile;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private AuthProvider authProvider;

    private String refreshToken;

    @Getter
    public enum Role {
        ROLE_USER, ROLE_ADMIN;
    }

    @Getter
    public enum AuthProvider{
        GOOGLE, NAVER;
    }
}
