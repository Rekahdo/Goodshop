package com.rekahdo.goodshop.user_service.dtos.paginations;

import com.rekahdo.goodshop.user_service.controllers.AppUserController;
import com.rekahdo.goodshop.user_service.dtos.entities.AppUserDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public final class AccountPageRequest extends ApiPageRequest<AppUserDto> {

    public AccountPageRequest() {
        super(methodOn(AppUserController.class).retrieveAll(null));
    }

}