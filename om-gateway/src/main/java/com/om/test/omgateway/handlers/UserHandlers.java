package com.om.test.omgateway.handlers;

import com.om.test.omgateway.model.MobileUserInfo;
import com.om.test.omgateway.proxies.UserProxy;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Service
public class UserHandlers {
    private UserProxy userService;

    public UserHandlers(UserProxy userService) {
        this.userService = userService;
    }

    public Mono<ServerResponse> getUserById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        Mono<MobileUserInfo> optionalMono = userService.findUserById(id);

        return optionalMono.flatMap(user -> {
            return ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(fromObject(user));
        });
    }
}
