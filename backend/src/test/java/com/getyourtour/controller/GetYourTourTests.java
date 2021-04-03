/*
 * File: GetYourTourTests.java
 * author: David Villalobos
 * Date: 2021/03/31
 */
package com.getyourtour.controller;

import java.util.List;
import com.getyourtour.model.*;
import com.getyourtour.service.ServiceCountry;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetYourTourTests {

	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void TestConnection() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
				String.class)).contains("RestFul Api of App GetYourTours");
	}

	@Test
	public void TestGetAllCountries() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/countries",
				String.class)).contains("[]");
	}

}
