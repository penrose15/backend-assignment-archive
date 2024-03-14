package com.example.jwtAndOAuth2.oauth;

import com.example.jwtAndOAuth2.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Getter
public class CustomUserDetails implements UserDetails, OAuth2User {
    private Long id;
    private String email;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public CustomUserDetails(Long id, String email, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.authorities = authorities;
    }

    public static CustomUserDetails create(Member member) {
        List<GrantedAuthority> authorities1 = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        return new CustomUserDetails(
                member.getMemberId(),
                member.getEmail(),
                authorities1
        );
    }

    public static CustomUserDetails create (Member member, Map<String, Object> attributes) {
        CustomUserDetails userDetails = CustomUserDetails.create(member);
        userDetails.setAttributes(attributes);
        return userDetails;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
