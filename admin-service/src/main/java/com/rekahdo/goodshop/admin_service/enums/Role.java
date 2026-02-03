package com.rekahdo.goodshop.admin_service.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum Role {
	
	ADMIN(1),
	MODERATOR(2),
	EDITOR(3),
	INSPECTOR(4),
	USER(5),
	VENDOR(6);

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