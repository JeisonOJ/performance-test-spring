package com.jeison.perfomance_test.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(info = @Info(
    title = "API",
    version = "1.0",
    description = "Documentation to the API"))
public class OpenApiConfig {

}
