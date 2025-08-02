package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class OTPNotFoundException extends ResponseStatusException {

	public OTPNotFoundException(Integer otp, String email) {
		super(HttpStatus.NOT_FOUND, String.format("OTP '%d' for email '%s' not found", otp, email));
	}

}