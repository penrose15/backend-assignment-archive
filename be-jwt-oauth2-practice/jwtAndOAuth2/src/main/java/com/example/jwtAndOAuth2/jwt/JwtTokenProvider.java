package com.example.jwtAndOAuth2.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.jwtAndOAuth2.member.repository.MemberRepository;
import com.example.jwtAndOAuth2.oauth.CustomUserDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Log4j2
@Component
public class JwtTokenProvider {

    private final String SECRET_KEY;
    private final String COOKIE_REFRESH_TOKEN_KEY;
    private final Long ACCESS_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60;
    private final Long REFRESH_TOKEN_EXPIRE_LENGTH = 1000L * 60 * 60 * 24 * 7;
    private final String AUTHORITIES_KEY = "role";

    @Autowired
    private MemberRepository memberRepository;

    public JwtTokenProvider(@Value("${app.auth.token.secret-key}")String SECRET_KEY, @Value("${app.auth.token.refresh-cookie-key}")String COOKIE_REFRESH_TOKEN_KEY) {
        this.SECRET_KEY = SECRET_KEY;
        this.COOKIE_REFRESH_TOKEN_KEY = COOKIE_REFRESH_TOKEN_KEY;

    }

    public String createAccessToken(
            Authentication authentication) {
        Date validity = new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPIRE_LENGTH);
        CustomUserDetails member = (CustomUserDetails) authentication.getPrincipal();

        String memberId = member.getName();
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        return JWT.create()
                .withSubject(memberId)
                .withClaim(AUTHORITIES_KEY, role)
                .withIssuer("jwt")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC512(SECRET_KEY));
    }

    public void createRefreshToken(Authentication authentication, HttpServletResponse response) {
        Date validity = new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRE_LENGTH);
        String refreshToken = JWT.create()
                .withIssuer("jwt")
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC512(SECRET_KEY));
        saveRefreshToken(authentication, refreshToken);

        ResponseCookie cookie = ResponseCookie.from(COOKIE_REFRESH_TOKEN_KEY, refreshToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .maxAge(REFRESH_TOKEN_EXPIRE_LENGTH)
                .path("/")
                .build();

        response.addHeader("Set-Cookie", cookie.toString());

    }

    private void saveRefreshToken(Authentication authentication, String refreshToken) {
        CustomUserDetails member = (CustomUserDetails) authentication.getPrincipal();
        Long id = Long.valueOf(member.getName());

        memberRepository.updateRefreshToken(id, refreshToken);

    }

    public Authentication getAuthentication(String accessToken) {

        DecodedJWT jwt = JWT.require(Algorithm.HMAC512(SECRET_KEY))
                        .build()
                        .verify(accessToken);

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(jwt
                        .getClaim(AUTHORITIES_KEY)
                        .asString().split(","))
                        .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        Long id = Long.valueOf(jwt.getSubject());
        CustomUserDetails principal = new CustomUserDetails(id, "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public Boolean validationToken(String token) {
        try {
            JWT.require(Algorithm.HMAC512(SECRET_KEY)).build().verify(token);
            return true;
        } catch (TokenExpiredException e) {
            System.out.println("만료된 토큰입니다.");
        } catch (IllegalStateException e) {
            System.out.println("jwt토근이 잘못 되었습니다");
        }
        return false;
    }

    private DecodedJWT decodedJWT(String accessToken) {
        return JWT.require(Algorithm.HMAC512(SECRET_KEY)).build().verify(accessToken);
    }

}