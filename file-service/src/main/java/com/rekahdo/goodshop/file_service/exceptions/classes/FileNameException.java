package com.rekahdo.goodshop.file_service.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class FileNameException extends ResponseStatusException {

	public FileNameException() {
		super(HttpStatus.BAD_REQUEST, "File name can not be null nor empty");
	}

}