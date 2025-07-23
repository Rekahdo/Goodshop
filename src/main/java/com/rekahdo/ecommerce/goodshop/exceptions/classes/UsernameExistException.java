package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class UsernameExistException extends Api_Exception {

	private final String username;

	public UsernameExistException(String username) {
		super(String.format("USER WITH NAME '%s' EXIST", username), HttpStatus.CONFLICT);
		this.username = username;
	}

}