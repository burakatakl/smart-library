package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Smart Library API",
        version = "1.0",
        description = "A RESTful API for managing a library system"
    )
)
public class SmartLibraryApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmartLibraryApplication.class, args);
    }
} 