package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UserIdNotFoundException extends Api_Exception {

	private final Long userId;

	public UserIdNotFoundException(Long userId) {
		super(String.format("USER WITH ID '%d' NOT FOUND", userId), HttpStatus.NOT_FOUND);
		this.userId = userId;
	}

}