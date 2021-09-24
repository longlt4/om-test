package com.om.test.omgateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class MobileUserController {
    @Value("${http://localhost:8081}")
    private URI home;

//    @GetMapping("/public/mobile/**")
//    public ResponseEntity<?> proxyPath(ProxyExchange<byte[]> proxy) throws Exception {
//        String path = proxy.path("/public/mobile/");
//        return proxy.uri(home.toString() + path).get();
//    }
}
