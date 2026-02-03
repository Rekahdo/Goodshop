package com.rekahdo.goodshop.otp_service.exceptions.classes;

import com.rekahdo.goodshop.otp_service.entities.Otp;
import com.rekahdo.goodshop.otp_service.enums.OtpPurpose;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class OTPNotVerifiedException extends ResponseStatusException {

	public OTPNotVerifiedException(Otp otp) {
		super(HttpStatus.NOT_FOUND, String.format("%s for '%s' has not been verified",
				OtpPurpose.findByIndex(otp.getPurpose()).title, otp.getSentTo()));
	}


}