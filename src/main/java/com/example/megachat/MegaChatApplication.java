package com.example.megachat;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info=@Info(title="MegaChat"))
@SpringBootApplication
public class MegaChatApplication {

    public static void main(String[] args) {
        SpringApplication.run(MegaChatApplication.class, args);
    }

}
