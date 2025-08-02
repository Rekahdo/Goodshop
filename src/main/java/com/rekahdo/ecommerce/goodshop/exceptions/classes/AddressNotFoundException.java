package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class AddressNotFoundException extends ResponseStatusException {

	public AddressNotFoundException() {
		super(HttpStatus.NOT_FOUND, "User address not found");
	}

}