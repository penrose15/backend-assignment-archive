package com.example.jwtAndOAuth2.jwt.filter;

import com.example.jwtAndOAuth2.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");


        try {
            if(StringUtils.hasText(token) && token.startsWith("Bearer ")) {
                token = token.substring(7);
            } else throw new RuntimeException("token이 존재하지 않거나 Bearer로 시작하지 않습니다.");

            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            log.debug("유효한 jwt토큰이 없습니다.");
        }
        filterChain.doFilter(request,response);
    }
}
