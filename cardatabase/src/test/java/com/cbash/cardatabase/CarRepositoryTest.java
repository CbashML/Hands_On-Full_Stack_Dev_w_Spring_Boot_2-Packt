package com.cbash.cardatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.cbash.cardatabase.domain.Car;
import com.cbash.cardatabase.domain.CarRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {
	
	@Autowired
	private TestEntityManager entityMng;
	
	@Autowired
	private CarRepository carRepo;
	
	//Test case...
	@Test
	public void saveCar() {
		Car car = new Car("Tesla", "Model X", "White", "ABZ-1235", 2021, 91000, null);
		
		entityMng.persistAndFlush(car);
		
		assertThat(car.getId()).isNotNull();
			
	}
	
	@Test
	public void deleteCars() {
		Car car01 = new Car("Tesla", "Model X", "White", "ABZ-1235", 2021, 91000, null);
		Car car02 = new Car("Mini", "Truck", "Yellow", "BWS-3117", 2020, 27000, null);
		
		entityMng.persistAndFlush(car01);
		entityMng.persistAndFlush(car02);
		
		carRepo.deleteAll();
		assertThat(carRepo.findAll()).isEmpty();
		
	}

}
