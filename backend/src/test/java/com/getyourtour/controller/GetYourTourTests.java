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
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GetYourTourTests {

	private ServiceCountry serviceCountry = new ServiceCountry();
	private final int port = 8001;

	@Test
	public void TestConnection() throws Exception {
		assertThat(serviceCountry).isNotNull();
	}

	@Test
	void TestCountry(){
		List<Country> list = serviceCountry.getAllCountries();
		// if(list.size() == 0 ){
			// add data
		// }
		// assertEquals(list.get(0).getName(), "Costa Rica");
		// assertEquals(list.get(1).getName(), "Mexico");
	}

}
