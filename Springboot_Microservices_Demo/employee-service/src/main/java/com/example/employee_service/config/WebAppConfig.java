package com.example.employee_service.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
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
}
