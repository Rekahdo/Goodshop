package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class NotAcceptableException extends ResponseStatusException {

	public NotAcceptableException(String data) {
		super(HttpStatus.NOT_ACCEPTABLE, String.format("Input is not acceptable: '%s'", data));
	}

}