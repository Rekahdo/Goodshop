package com.rekahdo.goodshop.authentication_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class AccountNotVerifiedException extends ResponseStatusException {

	public AccountNotVerifiedException() {
		super(HttpStatus.NOT_FOUND, "Account exists but not verified");
	}

	public AccountNotVerifiedException(String usernameEmail) {
		super(HttpStatus.NOT_FOUND, String.format("Account '%s' exists but not verified. ", usernameEmail));
	}

}