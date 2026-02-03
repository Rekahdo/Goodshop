package com.rekahdo.goodshop.user_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class AccountNotVerifiedException extends ResponseStatusException {

	public AccountNotVerifiedException() {
		super(HttpStatus.NOT_FOUND, "Account not verified");
	}

	public AccountNotVerifiedException(String username) {
		super(HttpStatus.NOT_FOUND, String.format("Account '%s' not verified", username));
	}

}