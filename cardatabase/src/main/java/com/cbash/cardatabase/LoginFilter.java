package com.cbash.cardatabase;

import java.io.IOException;import java.util.Collection;
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
	public LoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest reqst, HttpServletResponse respns)
			throws AuthenticationException, IOException, ServletException {	
		System.out.println("INPUT STREAM:" + reqst.getInputStream().read());
		System.out.println(
				"\nUSERNAME: " + reqst.getParameter("username") 
		+ "\nAUTH_TYPE: " + reqst.getAuthType()
//		+ "\nREADER: " + reqst.getReader()
				);
		AccountCredentials credtls = new ObjectMapper()
				.readValue(
						reqst.getInputStream(), 
						AccountCredentials.class
						);

		System.out.println(
				credtls.getUsername()
				);
		return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(
							credtls.getUsername(),
							credtls.getPassword(),
							Collections.emptyList()
							)
				);
		
//		String username = reqst.getParameter("username");
//		String password = reqst.getParameter("password");
//		System.out.printf("\nUSER: %s, PASS: %s", username, password);
//		return new UsernamePasswordAuthenticationToken(
//				username, 
//				password,
//				Collections.emptyList()
//			);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest reqst, HttpServletResponse respns, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
//		super.successfulAuthentication(reqst, respns, chain, auth);
		AuthenticationService.addToken(respns, auth.getName());
	}
	
}
