package com.streams.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.streams.poc"))
                .paths(regex("/fileData.*"))
                .build()
                .apiInfo(metaData());
    }
	
    private ApiInfo metaData() {
    	    return new ApiInfo(
    	      "Talentica POC REST API", 
    	      "Some custom description of API.", 
    	      "API TOS", 
    	      "Terms of service", 
    	      new Contact("Abhishek Kumar", "www.example.com", "myeaddress@company.com"), 
    	      "License of API", "API license URL", Collections.emptyList());
    }

}
