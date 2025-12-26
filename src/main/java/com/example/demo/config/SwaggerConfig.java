package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Insurance Fraud Detection API")
                        .version("1.0")
                        .description("API for Insurance Fraud Pattern Detection System")
                )
                // Multiple servers - both local and deployed
                .servers(List.of(
                        new Server()
                                .url("http://localhost:3306")
                                .description("Local Development Server"),
                        new Server()
                                .url("https://9084.32procr.amypo.ai/")
                                .description("Production Server")
                ))
                // JWT Security
                .addSecurityItem(
                        new SecurityRequirement().addList("Bearer Authentication")
                )
                .components(
                        new Components().addSecuritySchemes(
                                "Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .name("Authorization")
                                        .description("Enter JWT token (without 'Bearer ' prefix)")
                        )
                );
    }
}