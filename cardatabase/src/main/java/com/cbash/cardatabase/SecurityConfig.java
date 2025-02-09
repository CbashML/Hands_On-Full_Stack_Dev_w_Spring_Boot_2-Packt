package com.cbash.cardatabase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		String loginPath = "/login";	
		AuthenticationManager authMng = authenticationManager();
		LoginFilter loginFilter = new LoginFilter(loginPath, authMng);
		AuthenticationFilter authFilter = new AuthenticationFilter();

		httpSecurity
			.csrf().disable().cors()
			.and()
			.authorizeRequests()
				.antMatchers(HttpMethod.POST, loginPath).permitAll()
					.anyRequest().authenticated()
				.and()
					// Filter for the api/login request
					.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class)
					// Filter for other requests to check JWT in header
					.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);	
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		java.util.List<String> allowAllList = new java.util.ArrayList<String>();
		allowAllList.add("*");

		config.setAllowedOrigins(allowAllList);
		config.setAllowedHeaders(allowAllList);
		config.setAllowedMethods(allowAllList);
		
		config.setAllowCredentials(true);
		config.applyPermitDefaultValues();
		
		src.registerCorsConfiguration("/**", config);
		
		return src;
	}

	 @Override
	protected void configure(AuthenticationManagerBuilder authenticationManager) throws Exception {
		authenticationManager.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
