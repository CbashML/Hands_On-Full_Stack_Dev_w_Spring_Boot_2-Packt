package com.cbash.cardatabase;

import com.cbash.cardatabase.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CarDatabaseApplication {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        // After adding this comment the application is restarted
        SpringApplication.run(CarDatabaseApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args -> {
            // Add owner objects and save these to db.
            Owner owner1 = new Owner("John", "Johnson");
            Owner owner2 = new Owner("Mary", "Robinson");
            ownerRepository.save(owner1);
            ownerRepository.save(owner2);

            // Save demo data to database
            Car car1 = new Car("Ford", "Mustang", "Red", "ADF-1122", 2018, 60000, owner1);
            Car car2 = new Car("BMW", "X1", "Black", "SSJ-3020", 2020, 87000, owner2);
            Car car3 = new Car("Audi", "A8", "White", "AOO-2280", 2021, 90000, owner2);
            carRepository.save(car1);
            carRepository.save(car2);
            carRepository.save(car3);

            PasswordEncoder pe = new BCryptPasswordEncoder();
            String userPass = pe.encode("user");
            String adminPass = pe.encode("admin");
            System.out.println("USER: " + userPass);
            System.out.println("ADMIN: " + adminPass);

            userRepository.save(new User("user", userPass, "USER"));
            userRepository.save(new User("admin", adminPass, "ADMIN"));
        };
    }

}
