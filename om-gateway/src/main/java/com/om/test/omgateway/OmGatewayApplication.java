package com.om.test.omgateway;

import com.om.test.omgateway.config.UserConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@ConfigurationPropertiesScan("com.om.tes.omgateway.config")
@ComponentScan
public class OmGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmGatewayApplication.class, args);
	}
}
