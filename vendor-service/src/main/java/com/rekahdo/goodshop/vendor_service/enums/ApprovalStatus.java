package com.rekahdo.goodshop.vendor_service.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum ApprovalStatus {

	PENDING(1),
	ON_GOING(2),
	DENIED(3),
	APPROVED(4);

	public final Integer index;

	ApprovalStatus(Integer index) {
		this.index = index;
	}

	public static ApprovalStatus findByIndex(Integer index){
		Optional<ApprovalStatus> optional = Arrays.stream(ApprovalStatus.values())
				.filter(role -> Objects.equals(role.getIndex(), index))
				.findFirst();
		return optional.orElse(null);
	}
    
}