package com.rekahdo.ecommerce.goodshop._dtos.paginations;

import com.rekahdo.ecommerce.goodshop._controllers.AppUserController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.AppUserDto;
import com.rekahdo.ecommerce.goodshop._entities.AppUser;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public final class AppUserPageRequestDto extends PageRequestDto<AppUser, AppUserDto>{

	public AppUserPageRequestDto() {
		setSize(5);
	}

}