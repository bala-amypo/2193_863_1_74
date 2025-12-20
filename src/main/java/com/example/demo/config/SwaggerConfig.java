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
                // ✅ Server URL for deployment
                .servers(List.of(
                        new Server().url("https://9335.pro604cr.amypo.ai")
                ))

                // ✅ API Information (required by test case)
                .info(new Info()
                        .title("Insurance Fraud Pattern Detector API")
                        .description("REST API for Insurance Fraud Detection using rules and claims")
                        .version("1.0"))

                // ✅ Apply JWT security to secured endpoints
                .addSecurityItem(
                        new SecurityRequirement().addList("Bearer Authentication")
                )

                // ✅ Define JWT Bearer Security Scheme
                .components(new Components()
                        .addSecuritySchemes(
                                "Bearer Authentication",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        ));
    }
}
