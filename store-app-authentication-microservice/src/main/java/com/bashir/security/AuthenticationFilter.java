package com.bashir.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bashir.model.LoginRequestModel;
import com.bashir.service.AuthenticationService;
import com.bashir.shared.EmployeeDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
	private Environment env;
	private AuthenticationService authenticationService;
	
    public AuthenticationFilter(Environment env, 
    		AuthenticationService authenticationService,
    		AuthenticationManager authManager) {
    	
    	this.env = env;
    	this.authenticationService = authenticationService;
	    super.setAuthenticationManager(authManager);
    }
	
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		
		try {
			
			LoginRequestModel creds = new ObjectMapper()
					.readValue(request.getInputStream(), 
							LoginRequestModel.class);
			
			return getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getEmail(), creds.getPassword(),
							new ArrayList<>()));
			
		}
		catch (IOException e) {
			
			throw new RuntimeException(e);
		}
		
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
	
		
		String username = ((User)authResult.getPrincipal()).getUsername();
		EmployeeDto employeeDetails = authenticationService
				                     .getEmployeeDetailsByEmail(username);
		
		String token = Jwts.builder()
				.setSubject(employeeDetails.getEmployeeId())
				.setExpiration(new Date(System.currentTimeMillis()
						+ Long.parseLong(env.getProperty("token.expiration.time"))))
				.signWith(SignatureAlgorithm.HS512, env.getProperty("token.secret"))
				.compact();
		
		response.addHeader("token", token);
		response.addHeader("employeeId", employeeDetails.getEmployeeId());
		response.addHeader("employeeType", employeeDetails.getEmployeeType());
		
	}

	
}
