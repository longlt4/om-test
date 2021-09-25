package com.om.test.omgateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties(prefix = "user.destinations")
public class UserDestinations {
    @NotNull
    public String userServiceUrl;

    public String getUserServiceUrl() {
        return userServiceUrl;
    }

    public void  setUserServiceUrl(String userServiceUrl) {
        this.userServiceUrl = userServiceUrl;
    }
}
