package com.rekahdo.goodshop.phone_service.exceptions.classes;

import com.rekahdo.goodshop.phone_service.enums.PhonePurpose;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class PhoneNotFoundException extends ResponseStatusException {

	public PhoneNotFoundException(Long userId) {
		super(HttpStatus.NOT_FOUND, String.format("User '%d' phone not found", userId));
	}

	public PhoneNotFoundException(Long userId, PhonePurpose purpose) {
		super(HttpStatus.NOT_FOUND, String.format("User '%d' %s phone not found", userId, purpose.name));
	}

	public PhoneNotFoundException(Long userId, String phoneNumber) {
		super(HttpStatus.NOT_FOUND, String.format("Phone number '%s' not found for User ID '%d'", phoneNumber, userId));
	}

}