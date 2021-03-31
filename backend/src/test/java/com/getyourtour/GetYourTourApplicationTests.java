package com.getyourtour;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import com.getyourtour.Country;


@SpringBootTest
class GetYourTourApplicationTests {

	private DaoModel db = new DaoModel();

	@Test
	void GetAllCountries(){
		List<Country> list = db.getAllCountries();
		// assertEquals(list.get(0).getName(), "Costa Rica");
		// assertEquals(list.get(1).getName(), "Mexico");
	}

}
