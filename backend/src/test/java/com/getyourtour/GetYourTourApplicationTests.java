/*
 * File: GetYourTourApplicationTests.java
 * author: David Villalobos
 * Date: 2021/03/31
 */
package com.getyourtour;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class GetYourTourApplicationTests {

	private DaoModel db = new DaoModel();

	@Test
	void TestCountry(){
		List<Country> list = db.getAllCountries();
		// if(list.size() == 0 ){
			// add data
		// }
		// assertEquals(list.get(0).getName(), "Costa Rica");
		// assertEquals(list.get(1).getName(), "Mexico");
	}

}
