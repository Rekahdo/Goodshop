package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class OTPExpiredException extends ResponseStatusException {

	public OTPExpiredException(Integer otp) {
		super(HttpStatus.EXPECTATION_FAILED, String.format("OTP '%d' has expired", otp));
	}

}