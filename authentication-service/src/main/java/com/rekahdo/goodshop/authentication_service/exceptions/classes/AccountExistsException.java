package com.rekahdo.goodshop.authentication_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class AccountExistsException extends ResponseStatusException {

	public AccountExistsException() {
		super(HttpStatus.FOUND, "Account exists");
	}

	public AccountExistsException(String email) {
		super(HttpStatus.FOUND, String.format("Account with username '%s' exists.", email));
	}

}