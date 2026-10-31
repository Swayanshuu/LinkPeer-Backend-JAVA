package com.swynx.linkpeer_backend.auth.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "LinkPeer API",
                version = "1.0",
                description = "LinkPeer API Documentation"
        ),
        security = @SecurityRequirement(name = "FirebaseAuth")
)
@SecurityScheme(
        name = "FirebaseAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "Enter your Firebase ID Token"
)
public class OpenApiConfig {
}
