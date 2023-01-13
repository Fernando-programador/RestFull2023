package com.br.fsc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {
	
	@Bean
	 OpenAPI customOpenApi() {
		return new OpenAPI()
				.info(new Info()
						.title("Rest_Full_2023 com java 17")
						.version("V1")
						.description("API complete de Fernando Corrêa 2023")
						.termsOfService("https://ajeitandoseulado.com.br")
						.license(new License().name("Apache 2.0")
								.url("https://github.com/Fernando-programador")));
	}

}
