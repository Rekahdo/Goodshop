package com.rekahdo.goodshop.forgot_password;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ForgotPasswordApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForgotPasswordApplication.class, args);
	}

}
