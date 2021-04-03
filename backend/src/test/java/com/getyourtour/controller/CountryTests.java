/*
 * File: CountryTests.java
 * author: David Villalobos
 * Date: 2021/03/31
 */
package com.getyourtour.controller;

import com.getyourtour.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CountryTests {

	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void TestInsertCountries() throws Exception {
		if(restTemplate.getForObject("http://localhost:" + port + "/countries", String.class).contains("[]")) {
			final String baseUrl = "http://localhost:" + port + "/country/";
			URI uri = new URI(baseUrl);
			Country c1 = new Country(null, "Costa Rica");
			Country c2 = new Country(null, "Mexico");
			Country c3 = new Country(null, "Colombia");
			HttpHeaders headers = new HttpHeaders();
			headers.set("X-COM-PERSIST", "true");

			HttpEntity<Country> request = new HttpEntity<>(c1, headers);
			ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
			assertEquals(200, result.getStatusCodeValue());

			request = new HttpEntity<>(c2, headers);
			result = this.restTemplate.postForEntity(uri, request, String.class);
			assertEquals(200, result.getStatusCodeValue());

			request = new HttpEntity<>(c3, headers);
			result = this.restTemplate.postForEntity(uri, request, String.class);
			assertEquals(200, result.getStatusCodeValue());
		}
	}

	@Test
	public void TestGetCountry() throws Exception {
		assertThat(restTemplate.getForObject("http://localhost:" + port + "/country?id=1",
				String.class)).contains("{\"id\":1,\"name\":\"Costa Rica\"}");
	}

	@Test
	public void TestGetAllCountries() throws Exception {
		assertThat(restTemplate.getForObject("http://localhost:" + port + "/countries",
				String.class)).contains("[{\"id\":1,\"name\":\"Costa Rica\"},{\"id\":2,\"name\":\"Mexico\"},{\"id\":3,\"name\":\"Colombia\"}]");
	}

}
