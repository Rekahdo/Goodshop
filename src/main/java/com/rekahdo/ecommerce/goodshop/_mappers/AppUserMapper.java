package com.rekahdo.ecommerce.goodshop._mappers;

import com.rekahdo.ecommerce.goodshop._controllers.AppUserController;
import com.rekahdo.ecommerce.goodshop._controllers.AuthorityController;
import com.rekahdo.ecommerce.goodshop._controllers.ProfileController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.AppUserDto;
import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface AppUserMapper extends Api_Mapper<AppUser, AppUserDto>{

	@Override
	@AfterMapping
	default void afterMappingToDto(@MappingTarget AppUserDto target, AppUser source) {
		target.add(linkTo(methodOn(AppUserController.class).getUser(source.getId())).withSelfRel());
		target.add(linkTo(methodOn(AuthorityController.class).getAuthority(source.getId())).withRel("authorities"));
		target.add(linkTo(methodOn(ProfileController.class).getProfile(source.getId())).withRel("profile"));
	}

}