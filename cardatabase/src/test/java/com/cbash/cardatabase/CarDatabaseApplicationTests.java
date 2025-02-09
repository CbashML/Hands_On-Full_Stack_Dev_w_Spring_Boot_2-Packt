package com.cbash.cardatabase;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

import com.cbash.cardatabase.web.CarController;


@RunWith(SpringRunner.class)
@SpringBootTest
class CarDatabaseApplicationTests {

	@Autowired
	private CarController carCntllr;
	
	@Test
	void contextLoads() {
		assertThat(carCntllr).isNotNull();
	}

}
