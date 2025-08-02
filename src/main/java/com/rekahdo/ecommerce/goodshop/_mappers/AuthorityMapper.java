package com.rekahdo.ecommerce.goodshop._mappers;

import com.rekahdo.ecommerce.goodshop._controllers.AppUserController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.AuthorityDto;
import com.rekahdo.ecommerce.goodshop._entities.Authority;
import com.rekahdo.ecommerce.goodshop.enums.AuthorityRole;
import com.rekahdo.ecommerce.goodshop.utilities.StringFormat;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface AuthorityMapper extends Api_Mapper<Authority, AuthorityDto> {

	@Override
	@AfterMapping
	default void afterMappingToDto(@MappingTarget AuthorityDto target, Authority source) {
		target.add(linkTo(methodOn(AppUserController.class).getUser(source.getAppUser().getId())).withRel("user"));
	}

}
