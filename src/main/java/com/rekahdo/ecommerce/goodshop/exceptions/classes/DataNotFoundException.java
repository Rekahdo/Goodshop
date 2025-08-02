package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class DataNotFoundException extends ResponseStatusException {

	public <T> DataNotFoundException(T value) {
		super(HttpStatus.NOT_FOUND, String.format("Data with value '%s' not found", value.toString()));
	}

}