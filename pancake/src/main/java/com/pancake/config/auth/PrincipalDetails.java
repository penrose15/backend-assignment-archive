package com.pancake.config.auth;

import com.pancake.model.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

//UserDetails 커스터마이징
//VO라고 보면 된다
public class PrincipalDetails implements UserDetails {
    //UserDetails는 사용자 정보를 담는 인터페이스이다.
    //구현시 spring security에서 구현한 클래스를 사용자 정보로 인식하고 인증작업을 한다.

    private Member member;

    public PrincipalDetails(Member member) {
        this.member = member;
    }
    //시큐리티는 /login 주소에 요청이 오면 대신 로그인을 진행함
    //Authentication타입 객체이며 안에 member 정보가 있어야 함

    //로그인 진행 완료 시 security session 만들어줌
    //Security session -> Authenticaion -> userDetails


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() { //User권한 리턴
                return member.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//암호 사용기간 지났는지 확인
        return true;
    }

    @Override
    public boolean isEnabled() { //사이트 규칙에 따라 return false로 설정
        return true;
    }
}
