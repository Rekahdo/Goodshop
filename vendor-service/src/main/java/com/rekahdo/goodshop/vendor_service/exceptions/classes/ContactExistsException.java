package com.rekahdo.goodshop.vendor_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ContactExistsException extends ResponseStatusException {

	public ContactExistsException() {
		super(HttpStatus.FOUND, "Vendor can only add a single contact person. Edit instead");
	}

}