package com.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.model.Member;
import com.jwt.oauth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter { //로그인 처리
    //아이디, 패스워드 데이터를 파싱하여 인증 요청을 위임하는 필터이다.

    private final AuthenticationManager authenticationManager;
    //인자로 받은 Authentication이 유효한 인증인지 확인하고, UserDetails를 통해 "Authentication" 객체를 리턴


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ObjectMapper om = new ObjectMapper();
            Member member = om.readValue(request.getInputStream(), Member.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();

            //loadUserByName()이 실행된 후 정상 작동이 되면 authentication 리턴

            return authentication;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //로그인 인증 성공 이후 과정

        System.out.println("successfulAuthentication");
        PrincipalDetails principalDetails = (PrincipalDetails) authResult.getPrincipal();

        String jwToken = JWT.create() //jwt토큰 생성
                .withSubject("jwt")
                .withExpiresAt(new Date(System.currentTimeMillis() + 60 * 1000 * 10)) //10분
                .withClaim("id", principalDetails.getMember().getId()) //payLoad부분
                .withClaim("username", principalDetails.getMember().getUsername()) //payLoad부분
                .sign(Algorithm.HMAC512("jwt"));
        response.addHeader("Authorization", "Bearer " + jwToken); //header부분
    }
}

