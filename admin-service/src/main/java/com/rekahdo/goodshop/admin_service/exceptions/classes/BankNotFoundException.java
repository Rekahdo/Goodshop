package com.rekahdo.goodshop.admin_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class BankNotFoundException extends ResponseStatusException {

	public BankNotFoundException() {
		super(HttpStatus.NOT_FOUND, "Vendor has not added their bank account details");
	}

}