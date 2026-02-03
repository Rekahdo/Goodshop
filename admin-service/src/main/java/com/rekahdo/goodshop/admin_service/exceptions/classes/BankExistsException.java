package com.rekahdo.goodshop.admin_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class BankExistsException extends ResponseStatusException {

	public BankExistsException() {
		super(HttpStatus.FOUND, "Vendor can only have a single bank account details. Edit instead");
	}

}