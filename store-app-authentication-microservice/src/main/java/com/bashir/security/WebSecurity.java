package com.bashir.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.bashir.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private Environment env;
    private AuthenticationService authenticationService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public WebSecurity(Environment env,
    		AuthenticationService authenticationService,
    		BCryptPasswordEncoder bCryptPasswordEncoder) {
    	
    	this.env = env;
    	this.authenticationService = authenticationService;
    	this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/**").permitAll()
		.and().addFilter(getAuthenticationFilter());
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
	auth.userDetailsService(authenticationService).passwordEncoder(bCryptPasswordEncoder);
	
	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception
	{
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(
				env, authenticationService, authenticationManager());
		return authenticationFilter;
	}
	
	

	
}
