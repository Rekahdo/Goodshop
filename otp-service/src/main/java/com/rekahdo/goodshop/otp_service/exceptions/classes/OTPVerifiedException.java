package com.rekahdo.goodshop.otp_service.exceptions.classes;

import com.rekahdo.goodshop.otp_service.entities.Otp;
import com.rekahdo.goodshop.otp_service.enums.OtpPurpose;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class OTPVerifiedException extends ResponseStatusException {

	public OTPVerifiedException(Otp otp) {
		super(HttpStatus.NOT_FOUND, String.format("%s for '%s' has been verified",
				OtpPurpose.findByIndex(otp.getPurpose()), otp.getSentTo()));
	}


}