package com.cbash.cardatabase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.cbash.cardatabase.domain.Car;
import com.cbash.cardatabase.domain.CarRepository;
import com.cbash.cardatabase.domain.Owner;
import com.cbash.cardatabase.domain.OwnerRepository;
import com.cbash.cardatabase.domain.User;
import com.cbash.cardatabase.domain.UserRepository;

import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CardatabaseApplication {
//	private static final Logger logger = LoggerFactory.getLogger(CardatabaseApplication.class);

	@Autowired
	private CarRepository repository;

	@Autowired
	private OwnerRepository orepository;
	
	@Autowired
	private UserRepository urepository;

	public static void main(String[] args) {
		// After adding this comment the application is restarted
		SpringApplication.run(CardatabaseApplication.class, args);
//		logger.info("Hello Spring Boot");
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			// Add owner objects and save these to db.
			Owner owner1 = new Owner("John", "Johnson");
			Owner owner2 = new Owner("Mary", "Robinson");
			orepository.save(owner1);
			orepository.save(owner2);

			// Save demo data to database
			Car car1 = new Car("Ford", "Mustang", "Red", "ADF-1122", 2018, 60000, owner1);
			Car car2 = new Car("BMW", "X1", "Black", "SSJ-3020", 2020, 87000, owner2);
			Car car3 = new Car("Audi", "A8", "White", "AOO-2280", 2021, 90000, owner2);
			repository.save(car1);
			repository.save(car2);
			repository.save(car3);
			
			PasswordEncoder pe = new BCryptPasswordEncoder();
			String userPass = pe.encode("user"); 
			String adminPass = pe.encode("admin");
			System.out.println("USER:"+userPass);
			System.out.println("ADMIN"+adminPass);
			
			urepository.save(new User("user", userPass, "USER"));
			urepository.save(new User("admin", adminPass, "ADMIN"));
		};
	}

}
