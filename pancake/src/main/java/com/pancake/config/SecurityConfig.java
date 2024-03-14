package com.pancake.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록된다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//SecurityFilterChain : HttpServletRequest와 일치시킬 수 있는 필터 체인을 정의합니다.
//HttpSecurity: 특정 http 요청에 대해 웹 기반 보안을 구성할 수 있습니다.

        http.csrf().disable(); //http.csrf()는 http로만 요청이 가능하게 함
        http.headers().frameOptions().disable(); //h2연결시 필요함
        //저게 응답 헤더가 해당 페이지를 <frame> 또는<iframe>, <object> 에서 렌더링할 수 있는지 여부를 나타내는데 사용된다 함(클릭재킹 공격 방지) 근데 이번에는 h2 때문에 disable()로 설정

        http.authorizeRequests()//httprequest시 필터링역할함
                .antMatchers("/user/**").authenticated() //"/user"인 요청은 인증되어야 함
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')") //ROLE_ADMIN or ROLE_MANAGER이면 통과(SpEL을 사용할 수 있다고 함)
                .antMatchers("/manager/**").access("hasRole('ROLE_ADMIN')") //ROLE_MANAGER이면 통과
                .anyRequest().permitAll()//싹다 허용
                .and().formLogin().loginPage("/login");
        //순서에 따라 적용이 되므로 순서 주의 필수
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
