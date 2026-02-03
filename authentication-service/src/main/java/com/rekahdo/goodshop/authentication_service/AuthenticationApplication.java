package com.rekahdo.goodshop.authentication_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthenticationApplication {

	// Just a comment for test
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

}
