package com.sofiasaless.desafiobackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Pic-pay simplificado",
        description = "API para pagamentos e transferências entre usuários.",
        version = "1"
    )
)
public class DesafiobackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafiobackendApplication.class, args);
	}

}
