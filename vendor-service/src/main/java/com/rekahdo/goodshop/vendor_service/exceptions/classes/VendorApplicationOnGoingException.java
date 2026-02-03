package com.rekahdo.goodshop.vendor_service.exceptions.classes;

import com.rekahdo.goodshop.vendor_service.enums.ApprovalStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class VendorApplicationOnGoingException extends ResponseStatusException {

	public VendorApplicationOnGoingException(Long id) {
		super(HttpStatus.FOUND, String.format("User '%d' has already applied to become a vendor and application is been revealed.", id));
	}

}