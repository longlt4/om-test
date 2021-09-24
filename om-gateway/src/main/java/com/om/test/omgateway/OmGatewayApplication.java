package com.om.test.omgateway;

import com.om.test.omgateway.config.UserConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import(UserConfiguration.class)
@ComponentScan
public class OmGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(OmGatewayApplication.class, args);
	}

//	@Bean
//	public RouteLocator mobileRoutes(RouteLocatorBuilder builder) {
//		return builder.routes()
//				.route(p -> p
//						.path("/users")
//						.filters(f -> f.addRequestHeader("hello", "would"))
//						.uri("http://localhost:8081/users"))
//						// .uri("google.com"))
//				.build();
//	}
}
