package com.om.test.omgateway.config;

import com.om.test.omgateway.model.MobileUserInfo;
import com.om.test.omgateway.service.UserService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Service
public class UserHandlers {
    private UserService userService;

    public UserHandlers(UserService userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
        // return userService.findUserById(id);
        String id = serverRequest.pathVariable("id");
        Mono<MobileUserInfo> optionalMono = userService.findUserById(id);

        return optionalMono.flatMap(user -> {
            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(fromObject(user));
        });
    }
}
