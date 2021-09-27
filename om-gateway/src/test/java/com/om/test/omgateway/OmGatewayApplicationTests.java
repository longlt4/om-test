package com.om.test.omgateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.om.test.omgateway.exception.UserServiceExceptionResponse;
import com.om.test.omgateway.model.MobileUserInfo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.reactive.function.client.WebClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = OmGatewayIntegrationTestConfiguration.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		properties={"user.destinations.userServiceUrl=http://localhost:8081"
})
@AutoConfigureWireMock(port=0)
class OmGatewayApplicationTests {
	@LocalServerPort
	private int port;

	@Test
	public void shouldGetUserById() throws JsonProcessingException {
		String userId = "1";

		MobileUserInfo expectedMobileUserInfo = new MobileUserInfo("username1", "username1@email.com");
		ObjectMapper objectMapper = new ObjectMapper();

		String body = objectMapper.writeValueAsString(expectedMobileUserInfo);

		String expectedPath = "/public/api/mobile/v1/users/" + userId;

		stubFor(get(urlEqualTo(expectedPath))
				.willReturn(aResponse()
						.withStatus(200)
						.withHeader("Content-Type", MediaType.APPLICATION_JSON.toString())
						.withBody(body)));

		WebClient client = WebClient.create("http://localhost:" + port + "/public/api/mobile/v1/users/" + userId);
		ResponseEntity<MobileUserInfo> z = client
				.get()
				.exchange()
				.flatMap(r -> r.toEntity(MobileUserInfo.class))
				.block();

		assertNotNull(z);
		assertEquals(body, expectedMobileUserInfo, z.getBody());
	}

	@Test
	public void shouldGetUserByIdNotFound() throws JsonProcessingException {
		String userId = "3";

		UserServiceExceptionResponse userServiceExceptionResponse = new UserServiceExceptionResponse("404", userId);
		ObjectMapper objectMapper = new ObjectMapper();

		String body = objectMapper.writeValueAsString(userServiceExceptionResponse);

		String expectedPath = "/public/api/mobile/v1/users/" + userId;

		stubFor(get(urlEqualTo(expectedPath))
				.willReturn(aResponse()
						.withStatus(404)
						.withHeader("Content-Type", MediaType.APPLICATION_JSON.toString())
						.withBody(body)));

		WebClient client = WebClient.create("http://localhost:" + port + "/public/api/mobile/v1/users/" + userId);
		ResponseEntity<UserServiceExceptionResponse> z = client
				.get()
				.exchange()
				.flatMap(r -> r.toEntity(UserServiceExceptionResponse.class))
				.block();

		assertNotNull(z);
		assertEquals(body, userServiceExceptionResponse, z.getBody());
	}

	@Test
	public void shouldGetUserByIdBadRequest() throws JsonProcessingException {
		String userId = "abcxyz";

		UserServiceExceptionResponse userServiceExceptionResponse = new UserServiceExceptionResponse("400", userId);
		ObjectMapper objectMapper = new ObjectMapper();

		String body = objectMapper.writeValueAsString(userServiceExceptionResponse);

		String expectedPath = "/public/api/mobile/v1/users/" + userId;

		stubFor(get(urlEqualTo(expectedPath))
				.willReturn(aResponse()
						.withStatus(400)
						.withHeader("Content-Type", MediaType.APPLICATION_JSON.toString())
						.withBody(body)));

		WebClient client = WebClient.create("http://localhost:" + port + "/public/api/mobile/v1/users/" + userId);
		ResponseEntity<UserServiceExceptionResponse> z = client
				.get()
				.exchange()
				.flatMap(r -> r.toEntity(UserServiceExceptionResponse.class))
				.block();

		assertNotNull(z);
		assertEquals(body, userServiceExceptionResponse, z.getBody());
	}
}
