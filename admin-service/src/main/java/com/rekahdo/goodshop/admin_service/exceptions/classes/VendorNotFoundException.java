package com.rekahdo.goodshop.admin_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class VendorNotFoundException extends ResponseStatusException {

	public VendorNotFoundException(Long id) {
		super(HttpStatus.NOT_FOUND, String.format("User '%d' is not a vendor", id));
	}

}