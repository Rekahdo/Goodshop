package com.rekahdo.goodshop.phone_service.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum PhonePurpose {
	
	PROFILE(1, "Profile"),
	BUSINESS(2, "Business"),
	CONTACT_PERSON(3, "Contact Person"),
	CONTACT_PERSON_EMERGENCY(4, "Contact Person Emergency");

	public final Integer index;
	public final String name;

	PhonePurpose(Integer index, String name) {
		this.index = index;
		this.name = name;
	}

	public static PhonePurpose findByIndex(Integer index){
		Optional<PhonePurpose> optional = Arrays.stream(PhonePurpose.values())
				.filter(purpose -> Objects.equals(purpose.index, index))
				.findFirst();
		return optional.orElse(null);
	}
    
}