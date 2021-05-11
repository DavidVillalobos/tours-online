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
public class HomeTests {

	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;

	private static final String prefix = "/api/v1";

	@Test
	public void TestIndex() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + prefix,
				String.class)).contains("RestFul Api of App GetYourTours");
	}

	@Test
	public void TestAbout() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + prefix + "/about",
				String.class)).contains("Get Your Tours :D \n Backend Build with Java, Spring boot and Gradle");
	}

	@Test
	public void TestAuthor() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + prefix + "/author",
				String.class)).contains("\"author\":\"Luis David Villalobos Gonzalez\",\"github\":\"https://github.com/DavidVillalobos\"");
	}

}
