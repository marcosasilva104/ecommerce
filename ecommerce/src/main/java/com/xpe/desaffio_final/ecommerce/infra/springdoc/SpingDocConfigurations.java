package com.xpe.desaffio_final.ecommerce.infra.springdoc;

import javax.sound.sampled.DataLine.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpingDocConfigurations {
	@Bean
	public OpenAPI customOpenAPI() {
	    return new OpenAPI()
	            .components(new Components()
	                    .addSecuritySchemes("bearer-key",
	                            new SecurityScheme()
	                                    .type(SecurityScheme.Type.HTTP)
	                                    .scheme("bearer")
	                                    .bearerFormat("JWT")))
	                    .info(((Object) new Info(null, null))
	                            .title("Ecommerce API")
	                            .description("API Rest da aplicação Ecommerce, contendo as funcionalidades de CRUD de cadastro de cliente, categoria, produto e solicitar pedidos")
	                            .contact(new Contact()
	                                    .name("Time Backend")
	                                    .email("backend@unicommerc"))
	                    .license(new License()
	                            .name("Apache 2.0")
	                            .url("http://ecommerce/api/licenca")));
	}
}