package ru.coderiders.bitemebee.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;

@Configuration
public class SwaggerConfig implements WebMvcConfigurer {
        @Bean
        SecurityConfiguration securityConfiguration() {
                return SecurityConfigurationBuilder.builder()
                        .enableCsrfSupport(false)
                        .useBasicAuthenticationWithAccessCodeGrant(true)
                        .build();
        }

        @Bean
        public Docket api() {
                return new Docket(DocumentationType.OAS_30)
                        .select()
                        .build();
        }

        @Bean
        public OpenAPI openAPI() {
                return new OpenAPI()
                        .addServersItem(new Server().url("http://localhost:8080"))
                        .components(new Components()
                                .addSecuritySchemes("Bearer",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                        )
                        .addSecurityItem(new SecurityRequirement()
                                .addList("Bearer")
                        )
                        .info(new Info()
                                .title("Система управления умной пасекой")
                                .version("0.0.1"));
        }

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
                registry.addRedirectViewController("/swagger-ui.html", "/swagger-ui/");
        }
}
