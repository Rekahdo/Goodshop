package com.rekahdo.goodshop.vendor_service.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum Role {
	
	SALES_MANAGER(1),
	OWNER(2),
	ACCOUNT_MANAGER(3);

	public final Integer index;

	Role(Integer index) {
		this.index = index;
	}

	public static Role findByIndex(Integer index){
		Optional<Role> optional = Arrays.stream(Role.values())
				.filter(role -> Objects.equals(role.getIndex(), index))
				.findFirst();
		return optional.orElse(null);
	}
    
}