package com.org.kraken.flex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApi {

	@Bean
	public OpenAPI customOpenAPI() {
//http://localhost:8085/swagger-ui/index.html
		return new OpenAPI().info(new Info()

				.title("KrakenFlex API")
				.version("0.0.1-SNAPSHOT")
				.description("KrakenFlex")
				.termsOfService("http://swagger.io/terms/")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));

	}
	
	@Bean
	public RestTemplate resttemplate() {
		return new RestTemplate();
	}

}
