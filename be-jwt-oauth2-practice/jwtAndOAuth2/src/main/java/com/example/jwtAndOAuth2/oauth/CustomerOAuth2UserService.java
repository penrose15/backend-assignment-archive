package com.example.jwtAndOAuth2.oauth;

import com.example.jwtAndOAuth2.member.entity.Member;
import com.example.jwtAndOAuth2.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        return super.loadUser(userRequest);
    }

    private OAuth2User process(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        Member.AuthProvider authProvider = Member.AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toLowerCase());
        Oauth2UserInfo userInfo = OauthUserInfoFactory.getOauth2UserInfo(authProvider, oAuth2User.getAttributes());

        if(userInfo.getEmail().isEmpty()) {
            throw new RuntimeException("Email not found from Oauth2 privider");
        }
        Optional<Member> memberOptional = memberRepository.findByEmail(userInfo.getEmail());
        Member member;

        if(memberOptional.isPresent()) {
            member = memberOptional.get();
            if(authProvider != member.getAuthProvider()) {
                throw new RuntimeException("Wrong Match Auth Provider");
            }
        } else {
            member = createMember(userInfo, authProvider);
        }
        return CustomUserDetails.create(member, oAuth2User.getAttributes());
    }

    private Member createMember(Oauth2UserInfo memberInfo, Member.AuthProvider authProvider) {
        Member member = new Member();
        member.setEmail(memberInfo.getEmail());
        member.setProfile(memberInfo.getProfile());
        member.setRole(Member.Role.ROLE_USER);
        member.setAuthProvider(authProvider);

        return memberRepository.save(member);
    }
}
