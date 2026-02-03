package com.rekahdo.goodshop.admin_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class BusinessExistsException extends ResponseStatusException {

	public BusinessExistsException() {
		super(HttpStatus.FOUND, "Vendor can only add a single business. Edit instead");
	}

}