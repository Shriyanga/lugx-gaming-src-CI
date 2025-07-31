package com.example.department_service.config;

import com.example.department_service.client.EmployeeClient;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author: Thrimal Avishka
 * @since: 2025-02-19
 */

@Configuration
@EnableWebMvc
@EnableJpaAuditing
public class WebAppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    public WebClient employeeWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl("http://employee-service:8082/api/v1/employee")
                .build();
    }

    @Bean
    public EmployeeClient employeeClient(WebClient employeeWebClient) {
        WebClientAdapter webClientAdapter = WebClientAdapter.create(employeeWebClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(webClientAdapter).build();
        return factory.createClient(EmployeeClient.class);
    }
}
