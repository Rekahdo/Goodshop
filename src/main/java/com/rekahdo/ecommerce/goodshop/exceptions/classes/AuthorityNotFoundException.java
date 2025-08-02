package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class AuthorityNotFoundException extends ResponseStatusException {

	public AuthorityNotFoundException() {
		super(HttpStatus.NOT_FOUND, "User does not have any special authorization");
	}

}