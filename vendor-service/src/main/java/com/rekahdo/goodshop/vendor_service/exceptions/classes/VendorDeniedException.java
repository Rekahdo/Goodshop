package com.rekahdo.goodshop.vendor_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class VendorDeniedException extends ResponseStatusException {

	public VendorDeniedException(Long id) {
		super(HttpStatus.FOUND, String.format("User '%d' was denied to become a vendor", id));
	}

}