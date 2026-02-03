package com.rekahdo.goodshop.otp_service.exceptions.classes;

import com.rekahdo.goodshop.otp_service.entities.Otp;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class OTPExpiredException extends ResponseStatusException {

	public OTPExpiredException() {
		super(HttpStatus.EXPECTATION_FAILED, "OTP has expired");
	}

	public OTPExpiredException(Otp otp) {
		super(HttpStatus.NOT_FOUND, String.format("OTP '%d' has expired", otp.getOtp()));
	}


}