package com.puntored.movies.config.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerDoc {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Film Proyect")
                                .description("Proyecto creado como requisito para puesto Semi-senior Puntored. " +
                                        "Permite la realización de CRUD de películas, categorías, inventario y tiendas")
                                .version("0.0.1")
                                .termsOfService("http://swagger.io/terms/"));
    }



}
