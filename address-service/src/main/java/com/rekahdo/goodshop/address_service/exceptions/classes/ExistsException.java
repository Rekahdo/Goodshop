package com.rekahdo.goodshop.address_service.exceptions.classes;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class ExistsException extends ResponseStatusException {

	public ExistsException() {
		super(HttpStatus.FOUND, "Data exists");
	}

	public ExistsException(String whatExists) {
		super(HttpStatus.FOUND, String.format("%s exists", StringUtils.capitalize(whatExists)));
	}

	public ExistsException(String whatExists, Long id) {
		super(HttpStatus.FOUND, String.format("%s '%d' exists", StringUtils.capitalize(whatExists), id));
	}

}