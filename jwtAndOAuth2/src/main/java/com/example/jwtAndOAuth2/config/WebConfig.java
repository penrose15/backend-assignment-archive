package com.example.jwtAndOAuth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    private final long MAX_AGE_SECS = 3600;

    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:3000");
        config.addAllowedHeader("*");
        config.addAllowedMethod("GET,POST,PUT,PATCH,DELETE,OPTIONS");
        config.addExposedHeader("Authorization");
        config.setMaxAge(MAX_AGE_SECS);

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
