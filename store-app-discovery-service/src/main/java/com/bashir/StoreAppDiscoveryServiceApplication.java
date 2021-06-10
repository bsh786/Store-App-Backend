package com.bashir;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class StoreAppDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StoreAppDiscoveryServiceApplication.class, args);
	}

}
