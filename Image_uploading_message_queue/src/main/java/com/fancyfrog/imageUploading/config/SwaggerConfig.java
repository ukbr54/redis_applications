package com.fancyfrog.imageUploading.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fancyfrog.imageUploading"))
                .paths(PathSelectors.any())
                .build().apiInfo(metaData())
                .useDefaultResponseMessages(false);
    }

    private ApiInfo metaData(){
        return new ApiInfoBuilder()
                .title("Spring Boot 2.0 File Upload example with Consul Integration,Swagger 2.8.0 & Message Queue using redis")
                .description("Upload file Swagger-ui 2.8.0 and Spring Boot 2 Spring Cloud Consul")
                .version("version 1.0")
                .contact(new Contact("Ujjwal Gupta",null,"ujjwal.kbr@gmail.com"))
                .build();
    }
}
