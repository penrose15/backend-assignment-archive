package com.example.jwtAndOAuth2.member.controller;

import com.example.jwtAndOAuth2.member.entity.Member;
import com.example.jwtAndOAuth2.member.repository.MemberRepository;
import com.example.jwtAndOAuth2.oauth.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Member getCurrentMember(@AuthenticationPrincipal CustomUserDetails member) {
        return memberRepository.findById(member.getId()).orElseThrow(() -> new IllegalStateException("notFoundMember"));
    }
}
