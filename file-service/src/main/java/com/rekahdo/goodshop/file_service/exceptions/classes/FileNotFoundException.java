package com.rekahdo.goodshop.file_service.exceptions.classes;

import com.rekahdo.goodshop.file_service.enums.Purpose;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class FileNotFoundException extends ResponseStatusException {

	public FileNotFoundException(Long userId, Purpose purpose) {
		super(HttpStatus.NOT_FOUND, String.format("User '%s' %s not found", userId, purpose.value));
	}

}