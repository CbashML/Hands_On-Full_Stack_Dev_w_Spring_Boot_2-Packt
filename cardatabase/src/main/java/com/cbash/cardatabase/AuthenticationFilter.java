package com.cbash.cardatabase;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.filter.GenericFilterBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.cbash.cardatabase.service.AuthenticationService;

public class AuthenticationFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		Authentication auth = AuthenticationService
								.getAuthentication((HttpServletRequest) request);
		SecurityContextHolder
			.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);
	}
	
}
