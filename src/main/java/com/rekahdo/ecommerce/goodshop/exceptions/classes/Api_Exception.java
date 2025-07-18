package com.rekahdo.ecommerce.goodshop.exceptions.classes;

import com.rekahdo.ecommerce.goodshop.enums.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class Api_Exception extends RuntimeException {

	private final HttpStatusCode statusCode;
	private final ErrorCode statusValue;

	public Api_Exception(String message,  HttpStatusCode statusCode, ErrorCode statusValue) {
		super(message);
		this.statusCode = statusCode;
		this.statusValue = statusValue;
	}

}
