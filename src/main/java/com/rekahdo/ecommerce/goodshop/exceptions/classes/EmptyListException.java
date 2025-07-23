package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import org.springframework.http.HttpStatus;

public class EmptyListException extends Api_Exception {

	public EmptyListException() {
		super("THE REQUEST RESOURCE HAS AN EMPTY LIST", HttpStatus.NO_CONTENT);
	}

}
