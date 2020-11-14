package com.packt.cardatabase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	private String username = "CarDatabase";
	private String password = "abcd1234";
	private String encryptedPassword = "$2a$10$XpwYGvxOqiZTViXq9MhP7OV7xq8IDRFXwxYDu0htHvV8ni8W8i8vO";
	private String roles = "USER";
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		super.configure(http);
	}

	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		super.configure(auth);
//		auth.inMemoryAuthentication()
//			.passwordEncoder(passwordEncoder())
//			.withUser(username)
//			.password(encryptedPassword)
//			.roles(roles);
//	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {

		System.out.printf(":::::::::ENCRYPTED PASSWORD: %s \n", passwordEncoder().encode(password));
		System.out.printf("ENCRYPTED PASSWORD: %s \n", encryptedPassword);
		@SuppressWarnings("deprecation")
		UserDetails user = User.withDefaultPasswordEncoder()
				.username(username)
				.password(password)
				.roles(roles)
				.build();
		return new InMemoryUserDetailsManager(user);
	}
	

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
