package com.rekahdo.goodshop.vendor_service.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum AccountType {

	SAVINGS(1),
	CURRENT(2);

	public final Integer index;

	AccountType(Integer index) {
		this.index = index;
	}

	public static AccountType findByIndex(Integer index){
		Optional<AccountType> optional = Arrays.stream(AccountType.values())
				.filter(role -> Objects.equals(role.getIndex(), index))
				.findFirst();
		return optional.orElse(null);
	}
    
}