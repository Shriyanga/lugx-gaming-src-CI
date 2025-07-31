package com.example.orderservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author: Raigam Marketing Services
 * @developer: Thrimal Avishka
 * @since: 26-Jul-25
 **/
@Configuration
@EnableJpaAuditing
public class WebAppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
