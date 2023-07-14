package com.sqli.intern.api.validator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Api Validation" ,
                                version = "1.0",
                                description ="Application for API validation"))
public class Application  {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
