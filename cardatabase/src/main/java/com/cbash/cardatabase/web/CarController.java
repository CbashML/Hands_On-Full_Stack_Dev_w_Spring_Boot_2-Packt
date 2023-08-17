package com.cbash.cardatabase.web;

import com.cbash.cardatabase.domain.Car;
import com.cbash.cardatabase.domain.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
	
	@Autowired
	private CarRepository repository;
	
	@GetMapping("/cars")
	public Iterable<Car> getCars() {
		return repository.findAll();
	}
	
}
