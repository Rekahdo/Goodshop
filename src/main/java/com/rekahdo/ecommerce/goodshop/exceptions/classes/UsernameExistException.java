package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import com.rekahdo.ecommerce.goodshop.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class UsernameExistException extends Api_Exception {

	private final String username;

	public UsernameExistException(String username) {
		super(String.format("USER WITH NAME '%s' EXIST", username), HttpStatus.FOUND, ErrorCode.USERNAME_EXIST_VIOLATION);
		this.username = username;
	}

}