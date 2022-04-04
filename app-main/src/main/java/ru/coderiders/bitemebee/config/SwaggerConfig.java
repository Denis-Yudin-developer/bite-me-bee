package ru.coderiders.BiteMeBee.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Система управления умной пасекой",
                description = "Учебный проект в рамках академии CodeRiders"
        ),
        servers = @Server(
                url = "http://localhost:8080",
                description = "Основной сервер"
        )
)
public class SwaggerConfig {
}
