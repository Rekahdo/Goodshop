package com.rekahdo.goodshop.user_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class IncorrectPasswordException extends ResponseStatusException {

	public IncorrectPasswordException() {
		super(HttpStatus.BAD_REQUEST, "Incorrect password");
	}

	public IncorrectPasswordException(String password) {
		super(HttpStatus.BAD_REQUEST, String.format("Incorrect password '%s'", password));
	}

}