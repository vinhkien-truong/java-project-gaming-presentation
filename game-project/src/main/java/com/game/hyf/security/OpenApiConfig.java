package com.game.hyf.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;
/*
    
    This class configures OpenAPI/Swagger documentation for the API.  
    It defines a global security scheme for JWT Bearer tokens,  
    which will be applied to all endpoints unless overridden.  
     
    In the Swagger UI, this will allow users to enter a JWT token once,  
    and it will be included in the Authorization header for all requests.  
     
    The @OpenAPIDefinition annotation sets the API title and version,  
    and applies the security requirement globally.  
    The @SecurityScheme annotation defines the details of the JWT Bearer scheme.  
     
    Note: The actual security enforcement is still handled by Spring Security in SecurityConfig.  
    This class only affects the API documentation and testing experience.   
*/
@Configuration
// This class configures OpenAPI/Swagger documentation for the API, including a global security scheme for JWT Bearer tokens.
// It uses the @OpenAPIDefinition annotation to set the API title and version, and to apply a security requirement globally.
@OpenAPIDefinition(
    info = @Info(title = "My Game API", version = "v1"),
    security = @SecurityRequirement(name = "bearerAuth") // Applies security globally
)
// Define the security scheme for JWT Bearer tokens
// The @SecurityScheme annotation defines the details of the JWT Bearer scheme, which will be used in the Swagger UI for authentication.
// The name "bearerAuth" must match the name used in the @SecurityRequirement annotation above.
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class OpenApiConfig {
}