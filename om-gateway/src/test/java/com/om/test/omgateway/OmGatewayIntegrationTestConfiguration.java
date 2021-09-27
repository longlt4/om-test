package com.om.test.omgateway;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class OmGatewayIntegrationTestConfiguration {
    @Bean
    public NettyReactiveWebServerFactory NettyReactiveWebServerFactory() {
        return new NettyReactiveWebServerFactory();
    }
}
