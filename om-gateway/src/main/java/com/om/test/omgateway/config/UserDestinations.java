package com.om.test.omgateway.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

@ConfigurationProperties(prefix = "users.destinations")
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
