package com.rekahdo.goodshop.admin_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class VendorExistsException extends ResponseStatusException {

	public VendorExistsException(Long id) {
		super(HttpStatus.FOUND, String.format("User '%d' is already a vendor", id));
	}

}