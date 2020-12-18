package com.cbash.cardatabase;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.cors.CorsConfiguration;

import com.cbash.cardatabase.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
		String loginPath = "/login";	
//		AuthenticationManager authMng = authenticationManager();
//		LoginFilter loginFilter = new LoginFilter(loginPath, authMng);
//		Class<UsernamePasswordAuthenticationFilter> beforeFilter = UsernamePasswordAuthenticationFilter.class;
//		AuthenticationFilter authFilter = new AuthenticationFilter();

		http
			.csrf().disable().cors()
			.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
					.anyRequest().authenticated()
				.and()
//					// Filter for the api/login request
					.addFilterBefore(new LoginFilter(loginPath, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
//					// Filter for other requests to check JWT in header
					.addFilterBefore(new AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);	
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		List<String> allowAllList = new java.util.ArrayList<String>();
//		String genericPath = "/**";
		allowAllList.add("*");
		
		
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowedMethods(Arrays.asList("*"));
		
		config.setAllowCredentials(true);
		// TODO : Try without this or not setAllowed* 
		config.applyPermitDefaultValues();
		
		src.registerCorsConfiguration("/**", config);
		
		return src;
	}

	 @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		super.configure(auth);
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	 	String username = "admin";
//	 	String encryptedPassword = passwordEncoder().encode("admin");
//	 	String roles = "Admin";
//		super.configure(auth);
//		auth.inMemoryAuthentication()
//			.passwordEncoder(passwordEncoder())
//			.withUser(username)
//			.password(encryptedPassword)
//			.roles(roles);
//	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
