package com.cbash.cardatabase.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.cbash.cardatabase.domain.Car;
import com.cbash.cardatabase.domain.CarRepository;

@org.springframework.web.bind.annotation.RestController
public class CarController {
	
	@Autowired
	private CarRepository repository;
	
	@GetMapping("/cars")
	public Iterable<Car> getCars() {
		return repository.findAll();
	}
	
}
