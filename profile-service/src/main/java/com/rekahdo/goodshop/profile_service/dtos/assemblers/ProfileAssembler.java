package com.rekahdo.goodshop.profile_service.dtos.assemblers;

import com.rekahdo.goodshop.profile_service.controllers.ProfileController;
import com.rekahdo.goodshop.profile_service.dtos.entities.ProfileDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfileAssembler extends ApiAssembler<ProfileDto> {

    @Override
    public EntityModel<ProfileDto> toModel(ProfileDto entity) {
        return EntityModel.of(entity,
                link(linkTo(methodOn(ProfileController.class).retrieve(entity.getUserId())), HttpMethod.GET),
                link(linkTo(methodOn(ProfileController.class).setNames(entity.getUserId(), null)), "set-names", HttpMethod.PUT),
                link(linkTo(methodOn(ProfileController.class).setDob(entity.getUserId(), null)), "set-dob", HttpMethod.PUT),
                link(linkTo(methodOn(ProfileController.class).setGender(entity.getUserId(), null)), "set-gender", HttpMethod.PUT)
        );
    }

}