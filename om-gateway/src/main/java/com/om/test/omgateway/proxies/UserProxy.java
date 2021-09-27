package com.om.test.omgateway.proxies;

import com.om.test.omgateway.config.UserDestinations;
import com.om.test.omgateway.exception.UserServiceException;
import com.om.test.omgateway.model.MobileUserInfo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class UserProxy {
    private UserDestinations userDestinations;
    private WebClient client;

    public UserProxy(UserDestinations userDestinations, WebClient.Builder webClientBuilder) {
        this.userDestinations = userDestinations;
        this.client = webClientBuilder.baseUrl(userDestinations.getUserServiceUrl()).build();
    }

    public Mono<MobileUserInfo> findUserById(String id) {
        Mono<ClientResponse> response = client
                .get()
                .uri(userDestinations.getUserServiceUrl() + "/users/{id}", id)
                .exchange();
        return response.flatMap(resp -> {
            switch (resp.statusCode()) {
                case OK:
                    return resp.bodyToMono(MobileUserInfo.class);
                case NOT_FOUND:
                    return Mono.error(new UserServiceException(HttpStatus.NOT_FOUND, id));
                default:
                    return Mono.error(new RuntimeException(id));
            }
        });
    }
}
