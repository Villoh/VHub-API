package dev.mikel_v.vhub_api.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenAPI Configuration.
 *
 * This class provides the configuration for OpenAPI documentation in the application.
 * It is responsible for defining the API metadata, such as title, description, version,
 * and other related information that will be included in the generated OpenAPI documentation.
 *
 * Note: This class is annotated with @Configuration to indicate that it is a configuration
 * class for OpenAPI documentation.
 *
 * OpenAPI is an industry-standard specification for documenting RESTful APIs. It allows developers
 * to describe the available endpoints, request/response structures, and other important information
 * for API consumers.
 *
 * @author Mikel Villota
 * @version 1.0
 * @since 2023-06-14
 *
 *
 */
@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("VLRHub - API")
                        .description("API for the VLRHub mobile application.")
                        .version("1.0.0"));
    }
}
