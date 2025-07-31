package com.example.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.server.WebFilter;

import java.util.List;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-25
 */
@Configuration
public class WebAppConfig {

    @Bean
    public WebFilter corsFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(List.of("*"));
        corsConfig.setAllowedHeaders(List.of("*"));
        corsConfig.setAllowedMethods(List.of("*"));
        corsConfig.setAllowCredentials(false);
        return new CorsWebFilter(request -> corsConfig);
    }
}
