package com.om.test.omgateway.service;

import com.om.test.omgateway.config.UserDestinations;
import com.om.test.omgateway.exception.UserNotFoundException;
import com.om.test.omgateway.model.MobileUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class UserService {
    private UserDestinations userDestinations;
    private WebClient client;

    public UserService(UserDestinations userDestinations, WebClient.Builder webClientBuilder) {
        this.userDestinations = userDestinations;
        this.client = webClientBuilder.baseUrl("http://localhost:8081").build();;
    }

    public Mono<MobileUserInfo> findUserById(String id) {
        Mono<MobileUserInfo> response = client.get()
                .uri("http://localhost:8081" + "/users/{id}", id)
                .exchangeToMono(res -> {
                    switch (res.statusCode()) {
                        case OK:
                            return res.bodyToMono(MobileUserInfo.class);
                        case NOT_FOUND:
                            return Mono.error(new UserNotFoundException(0L));
                        default:
                            return Mono.error(new RuntimeException("Unknown" + res.statusCode()));
                    }
                });

        return response;
    }
}
