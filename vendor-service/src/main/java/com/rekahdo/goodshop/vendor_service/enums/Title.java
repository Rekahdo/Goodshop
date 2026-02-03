package com.rekahdo.goodshop.vendor_service.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum Title {

	MR(1),
	MRS(2),
	MISS(3);

	public final Integer index;

	Title(Integer index) {
		this.index = index;
	}

	public static Title findByIndex(Integer index){
		Optional<Title> optional = Arrays.stream(Title.values())
				.filter(role -> Objects.equals(role.getIndex(), index))
				.findFirst();
		return optional.orElse(null);
	}
    
}