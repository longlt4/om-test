package com.om.test.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
public class UserServiceApplicationTests {
	@Autowired
	private WebTestClient webClient;

	@Test
	void contextLoads() {

	}

}
