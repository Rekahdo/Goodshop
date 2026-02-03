package com.rekahdo.goodshop.profile_service.exceptions.classes;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class NotFoundException extends ResponseStatusException {

	public NotFoundException() {
		super(HttpStatus.NOT_FOUND, "Data not found");
	}

	public NotFoundException(String whatNotExists) {
		super(HttpStatus.NOT_FOUND, String.format("%s not found", StringUtils.capitalize(whatNotExists)));
	}

	public NotFoundException(String whatNotExists, Long id) {
		super(HttpStatus.NOT_FOUND, String.format("%s '%d' not found", StringUtils.capitalize(whatNotExists), id));
	}

	public NotFoundException(String whatNotExists, String id) {
		super(HttpStatus.NOT_FOUND, String.format("%s '%s' not found", StringUtils.capitalize(whatNotExists), id));
	}

}