package com.rekahdo.goodshop.vendor_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ContactNotFoundException extends ResponseStatusException {

	public ContactNotFoundException(Long id) {
		super(HttpStatus.NOT_FOUND, String.format("User '%d' is not a vendor", id));
	}

}