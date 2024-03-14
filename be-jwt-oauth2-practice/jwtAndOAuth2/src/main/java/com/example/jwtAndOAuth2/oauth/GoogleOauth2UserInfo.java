package com.example.jwtAndOAuth2.oauth;

import java.util.Map;

public class GoogleOauth2UserInfo extends Oauth2UserInfo{
    public GoogleOauth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getProfile() {
        return (String) attributes.get("profile");
    }
}
