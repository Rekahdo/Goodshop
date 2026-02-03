package com.rekahdo.goodshop.address_service.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum AddressPurpose {
	
	PROFILE(1, "Profile"),
	BUSINESS(2, "Business"),
	BANK(3, "Bank");

	public final Integer index;
	public final String value;

	AddressPurpose(Integer index, String value) {
		this.index = index;
		this.value = value;
	}

	public static AddressPurpose findByIndex(Integer index){
		Optional<AddressPurpose> optional = Arrays.stream(AddressPurpose.values())
				.filter(purpose -> Objects.equals(purpose.index, index))
				.findFirst();
		return optional.orElse(null);
	}
    
}