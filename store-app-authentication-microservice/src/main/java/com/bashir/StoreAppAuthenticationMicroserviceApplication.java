package com.bashir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class StoreAppAuthenticationMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreAppAuthenticationMicroserviceApplication.class, args);
	}
	
	
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
