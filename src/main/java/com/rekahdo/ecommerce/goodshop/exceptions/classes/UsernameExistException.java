package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Getter
public class UsernameExistException extends ResponseStatusException {

	private final String username;

	public UsernameExistException(String username) {
		super(HttpStatus.CONFLICT, String.format("USER WITH NAME '%s' EXIST", username));
		this.username = username;
	}

}