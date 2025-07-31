package com.example.api_gateway.filter;

import com.example.api_gateway.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-26
 */

@Component
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    @Value("${allowed-routes}")
    private List<String> allowedRoutes;

    public JwtFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String requestPath = exchange.getRequest().getURI().getPath();
            if (allowedRoutes.stream().anyMatch(requestPath::startsWith)) {
                return chain.filter(exchange);
            }
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                throw new RuntimeException("Missing authorization header");
            }
            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7);
            } else {
                throw new RuntimeException("Invalid authorization header format");
            }
            try {
                jwtUtil.validateToken(authHeader);
            } catch (Exception e) {
                System.out.println("Invalid access...!");
                throw new RuntimeException("Unauthorized access to application");
            }
            return chain.filter(exchange);
        };
    }

    public static class Config {
    }
}
