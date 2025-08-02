package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class UserIdNotFoundException extends ResponseStatusException {

	public UserIdNotFoundException() {
		super(HttpStatus.NOT_FOUND, "USER ID NOT FOUND".toUpperCase());
	}

}