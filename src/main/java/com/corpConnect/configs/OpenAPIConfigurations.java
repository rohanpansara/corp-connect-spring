package com.corpConnect.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Rohan Pansara",
                        email = "rohan@gmail.com",
                        url = "#"
                ),
                description = "CC (Corp Connect) Web Application APIs",
                title = "CC APIs",
                version = "v1",
                termsOfService = "You have to like the repository before cloning ;)"
        ),
        servers = {
                @Server(
                        description = "Local Environment",
                        url = "http://localhost:8089"
                ),
                @Server(
                        description = "Production Environment",
                        url = "#"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenAPIConfigurations {
}
