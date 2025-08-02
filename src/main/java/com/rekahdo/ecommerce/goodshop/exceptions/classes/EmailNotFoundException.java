package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class EmailNotFoundException extends ResponseStatusException {

	public EmailNotFoundException(String email) {
		super(HttpStatus.NOT_FOUND, String.format("USER WITH EMAIL '%s' NOT FOUND", email));
	}

}