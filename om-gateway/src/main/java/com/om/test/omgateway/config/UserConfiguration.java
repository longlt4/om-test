package com.om.test.omgateway.config;

import com.om.test.omgateway.service.UserService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
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
                        .path("/users/{id}")
                        .and().method("GET")
                        .uri("http://localhost:8081"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> userHandlerRouting(UserHandlers handlers) {
        return RouterFunctions.route(GET("/users/{id}"), handlers::getUserById);
    }

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }
}
