package com.packt.cardatabase.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepository extends CrudRepository <Car, Long> {
	
//	// Fetch cars my brand
//	List<Car> findByBrand(String brand);
//	
//	//Fetch cars by color
//	List<Car> findByColor(String color);
//	
//	// Fetch cars by year
//	List<Car> findByYear(int year);	
//	
//	// Fetch cars by brand and model
//	List<Car> findByBrandAndModel(String Brand, String Model);
//	
//	// Fetch cars by brand or color
//	List<Car> findByBrandOrColor(String Brand, String Color);
//	
//	// Fetch cars by brand and sort by year ascending
//	List<Car> findByBrandOrderByYearAsc(String brand);
//	
//	// Fetch cars by brand using SQL
//	@Query("select c from Car c where c.brand = ?1")
//	List<Car> queryByBrand(String brand);
//
//	// Fetch cars by brand using SQL
//	@Query("select c from Car where brand like %?1")
//	List<Car> findByBrandEndsWith(String brand);
	
}

