package com.example.gameservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.converter.json.GsonBuilderUtils;

/**
 * @author Thrimal Avishka
 * @since 12-Jul-25
 **/
@Configuration
@EnableJpaAuditing
public class WebAppConfig {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
