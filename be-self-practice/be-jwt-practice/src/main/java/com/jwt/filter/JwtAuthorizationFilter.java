package com.jwt.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jwt.model.Member;
import com.jwt.oauth.PrincipalDetails;
import com.jwt.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {
    // 권한 및 인증이 필요한 주소를 요청 시 BasicAuthenticationFilter를 반드시 진행

    private MemberRepository memberRepository;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("인증이나 권한이 필요한 주소 요청됨");

        String jwtHeader = request.getHeader("Authorization");
        //헤더를 가져와 토큰을 가지고 있는지 체크함

        if(jwtHeader==null || !jwtHeader.startsWith("Bearer")) { //시작이 Bearer가 아니거나 null일 경우
            chain.doFilter(request,response); //다음 필터로 넘김
            return;
        }

        String jwtToken = jwtHeader.replace("Bearer ","");
        String username = JWT.require(Algorithm.HMAC512("jwt")).build().verify(jwtToken).getClaim("username").asString();
        //토큰의 username 복호화해서 확인, 서비스에 등록한 유저인지 확인한다.

        if(username != null) { //만약 유저네임이 존재한다면
            Member member = memberRepository.findByUsername(username);


            PrincipalDetails principalDetails= new PrincipalDetails(member);
            Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication); //저장함

            chain.doFilter(request,response); //다음 필터로 넘어가기
        }
        super.doFilterInternal(request, response, chain);
        //dofilterInternal()는 인증이나 권한이 필요한 주소 요청이 있을을 때마다 해당 필터를 통하게 되어있음
    }
}
