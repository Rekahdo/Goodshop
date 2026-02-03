package com.rekahdo.goodshop.vendor_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class VendorApprovedException extends ResponseStatusException {

	public VendorApprovedException(Long id) {
		super(HttpStatus.FOUND, String.format("User '%d' is already an approved vendor", id));
	}

}