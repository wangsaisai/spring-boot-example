package com.bamboo.spring.boot.example.config;

import java.util.Collections;
import java.util.Set;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES = Collections
      .singleton("application/json");

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("Spring Boot Example\n" +
            "V1.0\n" +
            "Swagger API")
        .version("1.0-SNAPSHOT")
        .description("Spring Boot Example Swagger API")
        .build();
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .produces(DEFAULT_PRODUCES_AND_CONSUMES)
        .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.bamboo.spring.boot.example"))
        .build();
  }
}