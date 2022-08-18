package com.example.obspringejercicios456.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails(){
        Contact contactInfo = new Contact("Agustin", "https://www.linkedin.com/in/aaaguirre/", "agustinaguirre3195@gmail.com");
        return new ApiInfo("Ejercicios 4, 5, 6 modificados para crear Spring Boot API REST: Laptop store",
                "Laptop store rest docs",
                "1.0",
                "http://www.google.com",
                contactInfo,
                "MIT",
                "http://www.google.com",
                Collections.EMPTY_LIST);
    }

    @Bean
    public InternalResourceViewResolver defaultViewResolver() {
        return new InternalResourceViewResolver();
    }
}
