package com.sofiasaless.desafiobackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI openAPI () {
        return new OpenAPI()
        .info(new Info().title("Desafio Pic-pay simplificado").description("API para pagamentos e transferências entre usuários.").version("1"));
    }
}
