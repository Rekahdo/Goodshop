package com.rekahdo.goodshop.user_service.mappers;

import com.rekahdo.goodshop.user_service.controllers.AppUserController;
import com.rekahdo.goodshop.user_service.dtos.clients.AppUserClient;
import com.rekahdo.goodshop.user_service.dtos.entities.AppUserDto;
import com.rekahdo.goodshop.user_service.dtos.request.AccountRequest;
import com.rekahdo.goodshop.user_service.entities.AppUser;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.ui.ModelMap;

import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface AppUserMapper extends EntityMapper<AppUser, AccountRequest, AppUserDto, AppUserClient> {

    @Override
    @AfterMapping
    default void afterMappingToClient(@MappingTarget AppUserClient target, AppUser source) {
        target.setSelf(linkTo(methodOn(AppUserController.class).retrieveById(source.getId())).toUri());
    }

}