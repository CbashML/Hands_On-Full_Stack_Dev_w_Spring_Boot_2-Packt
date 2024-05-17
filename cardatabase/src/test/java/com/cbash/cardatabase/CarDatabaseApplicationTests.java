package com.cbash.cardatabase;

import com.cbash.cardatabase.web.CarController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;




class CarDatabaseApplicationTests {

	@InjectMocks
	private CarController carController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void contextLoads() {
		Assertions.assertNotNull(carController);
	}

}
