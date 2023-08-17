package com.cbash.cardatabase;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.FilterChain;

import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.cbash.cardatabase.domain.AccountCredentials;
import com.cbash.cardatabase.service.AuthenticationService;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LoginFilter extends AbstractAuthenticationProcessingFilter 
{
	public LoginFilter(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(
			HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {	
		
		AccountCredentials credentials = new ObjectMapper()
				.readValue(
						request.getInputStream(),
						AccountCredentials.class
						);

		return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(
							credentials.getUsername(),
							credentials.getPassword(),
							Collections.emptyList()
							)
				);
	}

	@Override
	protected void successfulAuthentication(
			HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain, Authentication authentication
			) throws IOException, ServletException {
		AuthenticationService.addToken(response, authentication.getName());
	}
	
}
