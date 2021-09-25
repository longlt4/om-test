package com.om.test.omgateway.proxies;

import com.om.test.omgateway.config.UserDestinations;
import com.om.test.omgateway.exception.UserServiceException;
import com.om.test.omgateway.model.MobileUserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class UserProxy {
    private UserDestinations userDestinations;
    private WebClient client;

    public UserProxy(UserDestinations userDestinations, WebClient.Builder webClientBuilder) {
        this.userDestinations = userDestinations;
        this.client = webClientBuilder.baseUrl(userDestinations.getUserServiceUrl()).build();;
    }

    public Mono<MobileUserInfo> findUserById(String id) {
        Mono<MobileUserInfo> response = client.get()
                .uri(userDestinations.getUserServiceUrl() + "/users/{id}", id)
                .exchangeToMono(res -> {
                    switch (res.statusCode()) {
                        case OK:
                            return res.bodyToMono(MobileUserInfo.class);
                        case NOT_FOUND:
                            return Mono.error(new UserServiceException(HttpStatus.NOT_FOUND, ""));
                        default:
                            return Mono.error(new RuntimeException("Unknown" + res.statusCode()));
                    }
                });

        return response;
    }
}
