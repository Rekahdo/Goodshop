package com.rekahdo.goodshop.authentication_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class TokenValidationException extends ResponseStatusException {

	public TokenValidationException() {
		super(HttpStatus.UNAUTHORIZED, "Token is invalid");
	}

}