package za.co.eblocks.assessment.warehouse.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import za.co.eblocks.assessment.warehouse.handler.ProductHandler;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
/*
@Configuration
public class WarehouseEndPointConfiguration {

    @Bean
    RouterFunction<ServerResponse> routes(ProductHandler handler) {
        return route(GET("/products"), handler::findAll) // <2>
                .andRoute(GET("/products/{id}"), handler::getById)
                .andRoute(DELETE("/products/{id}"), handler::deleteById) // <3>
                .andRoute(POST("/products"), handler::create);
    }
}*/