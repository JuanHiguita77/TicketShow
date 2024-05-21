package com.riwi.ticketShowWeb.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

//Notacion para darle configs a los beans
@Configuration
//Config inicial de swagger
@OpenAPIDefinition(
    info = @Info(title = "Api Online Ticket Buy",
                version = "1.0",
                description = "Api Created for online ticket shop using Java + SpringBoot and JPA")
)
public class OpenApiConfig 
{
    //Ruta: /api/v1/swagger-ui/index.html   
}
