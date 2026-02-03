package com.rekahdo.goodshop.file_service.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum Purpose {

	DISPLAY_PICTURE(1, "Display Picture"),
	BUSINESS_CERTIFICATE(2, "Business Certificate"),
	BUSINESS_REGISTRATION_DOCUMENTS(3, "Business Registration Documents"),
	CONTACT_PERSON_ID_PROOF(4, "Contact Person ID Proof");

	public final Integer index;
	public final String value;

	Purpose(Integer index, String value) {
		this.index = index;
		this.value = value;
	}

	public static Purpose findByIndex(Integer index){
		Optional<Purpose> optional = Arrays.stream(Purpose.values())
				.filter(fileType -> Objects.equals(fileType.getIndex(), index))
				.findFirst();
		return optional.orElse(null);
	}
    
}