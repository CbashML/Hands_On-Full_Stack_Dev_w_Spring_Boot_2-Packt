package com.cbash.cardatabase.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;

import static java.util.Collections.emptyList;

public class AuthenticationService {

	static final long EXPIRATIONTIME = 864_000_00; // One day in milliseconds
	static final String SIGNINGKEY = "Cbash'sSecretKey";
	static final String PREFIX = "Bearer";
	
	// Add token to Authorization header
	static public void addToken(HttpServletResponse respns, String username) {
		String JwtToken = Jwts.builder().setSubject(username)
				
				.setExpiration(
							new Date(System.currentTimeMillis() + EXPIRATIONTIME)
						)
				
				.signWith(SignatureAlgorithm.HS512, SIGNINGKEY)
				
				.compact();
		
		respns.addHeader("Authorization", PREFIX + " " + JwtToken);
		
		respns.addHeader(
					"Access-Control-Expose-Headers",
					"Authorization"
				);
	}
	
	// Get token from Authorization header
	static public Authentication getAuthentication(HttpServletRequest reqst) {
		String token = reqst.getHeader("Authorization");
		
		if (token != null) {
			String user = Jwts.parser()
					
					.setSigningKey(SIGNINGKEY)
					
					.parseClaimsJws(token.replace(PREFIX, ""))
					
					.getBody()
					
					.getSubject();
			
		if (user != null)
			return 
				new UsernamePasswordAuthenticationToken(
							user, 
							null, 
							emptyList()
						);
		}
		return null;
	}	
	
}
