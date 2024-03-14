package com.example.jwtAndOAuth2.oauth;

import com.example.jwtAndOAuth2.member.entity.Member;

import java.util.Map;

public class OauthUserInfoFactory {
    public static Oauth2UserInfo getOauth2UserInfo(Member.AuthProvider authProvider, Map<String, Object> attributes) {
        switch (authProvider) {
            case GOOGLE:return new GoogleOauth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type");
        }
    }
}
