package com.rekahdo.goodshop.otp_service.exceptions.classes;

import com.rekahdo.goodshop.otp_service.entities.Otp;
import com.rekahdo.goodshop.otp_service.enums.OtpPurpose;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class OTPNotFoundException extends ResponseStatusException {

	public OTPNotFoundException(Integer otp) {
		super(HttpStatus.NOT_FOUND, String.format("OTP '%d' not found. Request an OTP and then verify OTP", otp));
	}

	public OTPNotFoundException(String sentTo, OtpPurpose purpose) {
		super(HttpStatus.NOT_FOUND, String.format("%s for '%s' not found. Request a %s OTP and then verify OTP",
				purpose.title, sentTo, purpose.name()));
	}

	public OTPNotFoundException(Otp otp) {
		super(HttpStatus.NOT_FOUND, String.format("%s for '%s' not found. Request a %s OTP and then verify OTP",
				OtpPurpose.findByIndex(otp.getPurpose()).title, otp.getSentTo(),
				OtpPurpose.findByIndex(otp.getPurpose()).name()));
	}

}