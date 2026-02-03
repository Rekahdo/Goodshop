package com.rekahdo.goodshop.authentication_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class TokenExpiredException extends ResponseStatusException {

	public TokenExpiredException() {
		super(HttpStatus.UNAUTHORIZED, "Token has expired");
	}

}