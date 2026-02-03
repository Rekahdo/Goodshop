package com.rekahdo.goodshop.user_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class UserUidOrEmailNotFoundException extends ResponseStatusException {

	public UserUidOrEmailNotFoundException(String username) {
		super(HttpStatus.NOT_FOUND, String.format("User with UID or Email '%s' not found", username));
	}

}