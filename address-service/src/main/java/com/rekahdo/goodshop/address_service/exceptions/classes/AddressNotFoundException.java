package com.rekahdo.goodshop.address_service.exceptions.classes;

import com.rekahdo.goodshop.address_service.enums.AddressPurpose;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class AddressNotFoundException extends ResponseStatusException {

	public AddressNotFoundException(Long userId) {
		super(HttpStatus.NOT_FOUND, String.format("Address not found for User ID '%d'", userId));
	}

	public AddressNotFoundException(Long userId, AddressPurpose purpose) {
		super(HttpStatus.NOT_FOUND, String.format("User '%d' %s address not found", userId, purpose.value));
	}

	public AddressNotFoundException(Long userId, Long addressId) {
		super(HttpStatus.NOT_FOUND, String.format("Address ID '%d' not found for User ID '%d'", addressId, userId));
	}

	public AddressNotFoundException(Long userId, String address) {
		super(HttpStatus.NOT_FOUND, String.format("Address '%s' not found for User ID '%d'", address, userId));
	}

}