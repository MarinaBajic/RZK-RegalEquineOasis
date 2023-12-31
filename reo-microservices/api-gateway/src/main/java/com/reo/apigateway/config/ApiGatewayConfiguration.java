package com.reo.apigateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/app/**").uri("lb://app-service"))
                .route(p -> p.path("/")
                        .filters(f -> f
                                .rewritePath("/", "/api/app"))
                                .uri("lb://app-service"))
                .route(p -> p.path("/home")
                        .filters(f -> f
                                .rewritePath("/home", "/api/app"))
                                .uri("lb://app-service"))
                .route(p -> p.path("/home/search")
                        .filters(f -> f
                                .rewritePath("/home", "/api/app"))
                                .uri("lb://app-service"))
                .route(p -> p.path("/favorite/**")
                        .filters(f -> f
                                .rewritePath("/favorite", "/api/app/favorite"))
                                .uri("lb://app-service"))
                .build();
    }
}
