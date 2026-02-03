package com.rekahdo.goodshop.vendor_service.exceptions.classes;

import com.rekahdo.goodshop.vendor_service.enums.ApprovalStatus;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Getter
public class VendorApplicationStatusException extends ResponseStatusException {

	public VendorApplicationStatusException(Long id, Integer approvalStatus) {
		super(HttpStatus.FOUND, String.format("User '%d' has already applied to become a vendor. Review Status: '%s'.",
				id, ApprovalStatus.findByIndex(approvalStatus)));
	}

}