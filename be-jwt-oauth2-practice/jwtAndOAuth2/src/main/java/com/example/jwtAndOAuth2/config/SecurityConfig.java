package com.example.jwtAndOAuth2.config;

import com.example.jwtAndOAuth2.cookie.CookieAuthorizationRequestRepository;
import com.example.jwtAndOAuth2.jwt.entryPoint.JwtAuthenticationEntryPoint;
import com.example.jwtAndOAuth2.jwt.filter.JwtAuthenticationFilter;
import com.example.jwtAndOAuth2.jwt.handler.JwtAccessDeniedHandler;
import com.example.jwtAndOAuth2.oauth.CustomerOAuth2UserService;
import com.example.jwtAndOAuth2.oauth.handler.OAuth2AuthenticationFailureHandler;
import com.example.jwtAndOAuth2.oauth.handler.OAuth2AuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    private final CustomerOAuth2UserService customerOAuth2UserService;
    private final CookieAuthorizationRequestRepository cookieAuthorizationRequestRepository;
    private final OAuth2AuthenticationSuccessHandler authenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler authenticationFailureHandler;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.httpBasic().disable().build();

        http.authorizeRequests()
                .antMatchers("/","/oauth2","/auth/**").permitAll()
                .antMatchers("/user").hasRole("ROLE_USER")
                .antMatchers("/admin").hasRole("ROLE_ADMIN")
                .anyRequest().authenticated();

        http.formLogin().disable()
                .oauth2Login()
                .authorizationEndpoint()
                    .authorizationRequestRepository(cookieAuthorizationRequestRepository)
                .and()
                .userInfoEndpoint().userService(customerOAuth2UserService)
                .and()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        http.exceptionHandling()
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                .accessDeniedHandler(jwtAccessDeniedHandler);

        http.logout().deleteCookies("JSESSIONID");
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
