package com.om.test.omgateway.config;

import com.om.test.omgateway.handlers.UserHandlers;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
@EnableConfigurationProperties(UserDestinations.class)
public class UserConfiguration {
    @Bean
    public RouteLocator userProxyRouting(RouteLocatorBuilder builder, UserDestinations userDestinations) {
        return builder.routes()
                .route(p -> p
                        .path("/public/api/mobile/v1/users/{id}")
                        .and().method("GET")
                        .uri(userDestinations.getUserServiceUrl()))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userHandlerRouting(UserHandlers handlers) {
        return RouterFunctions.route(GET("/public/api/mobile/v1/users/{id}"), handlers::getUserById);
    }
}
