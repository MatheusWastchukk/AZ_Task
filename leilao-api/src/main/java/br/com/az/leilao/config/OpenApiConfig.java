package br.com.az.leilao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AZ Leilao API")
                        .description("Documentacao da API REST do teste tecnico AZ.")
                        .version("1.0.0")
                        .contact(new Contact().name("Matheus").email("matheus@example.local")));
    }
}
