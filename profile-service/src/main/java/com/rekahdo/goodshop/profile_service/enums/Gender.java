package com.rekahdo.goodshop.profile_service.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum Gender {
	
	MALE(1),
	FEMALE(2),
	CONFIDENTIAL(3);

	public final Integer index;

	Gender(Integer index) {
		this.index = index;
	}

	public static Gender findByIndex(Integer index){
		Optional<Gender> optional = Arrays.stream(Gender.values())
				.filter(gender -> Objects.equals(gender.getIndex(), index))
				.findFirst();
		return optional.orElse(null);
	}
    
}