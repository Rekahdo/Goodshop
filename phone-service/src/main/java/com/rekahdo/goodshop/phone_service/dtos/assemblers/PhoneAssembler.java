package com.rekahdo.goodshop.phone_service.dtos.assemblers;

import com.rekahdo.goodshop.phone_service.controllers.BusinessPhoneController;
import com.rekahdo.goodshop.phone_service.controllers.ContactPersonPhoneController;
import com.rekahdo.goodshop.phone_service.controllers.PhoneController;
import com.rekahdo.goodshop.phone_service.controllers.ProfilePhoneController;
import com.rekahdo.goodshop.phone_service.dtos.entities.PhoneDto;
import com.rekahdo.goodshop.phone_service.enums.PhonePurpose;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PhoneAssembler extends ApiAssembler<PhoneDto> {

    @Override
    public EntityModel<PhoneDto> toModel(PhoneDto entity) {
        if(Objects.equals(PhonePurpose.PROFILE, entity.getPurpose()))
            return EntityModel.of(entity,
                        link(linkTo(methodOn(ProfilePhoneController.class).set(entity.getUserId(), null)), "set-profile-number", HttpMethod.PUT),
                        link(linkTo(methodOn(ProfilePhoneController.class).verify(entity.getUserId())), "verify-profile-number", HttpMethod.PUT),
                        link(linkTo(methodOn(ProfilePhoneController.class).retrieve(entity.getUserId())), "retrieve-profile-number", HttpMethod.GET)
            );
        else if(Objects.equals(PhonePurpose.BUSINESS, entity.getPurpose()))
            return EntityModel.of(entity,
                    link(linkTo(methodOn(BusinessPhoneController.class).set(entity.getUserId(), null)), "set-business-number", HttpMethod.PUT),
                    link(linkTo(methodOn(BusinessPhoneController.class).verify(entity.getUserId())), "verify-business-number", HttpMethod.PUT),
                    link(linkTo(methodOn(BusinessPhoneController.class).retrieve(entity.getUserId())), "retrieve-business-number", HttpMethod.GET)
            );
        else if(Objects.equals(PhonePurpose.CONTACT_PERSON, entity.getPurpose()))
            return EntityModel.of(entity,
                    link(linkTo(methodOn(ContactPersonPhoneController.class).set(entity.getUserId(), null)), "set-contact-person-number", HttpMethod.PUT),
                    link(linkTo(methodOn(ContactPersonPhoneController.class).verify(entity.getUserId())), "verify-contact-person-number", HttpMethod.PUT),
                    link(linkTo(methodOn(ContactPersonPhoneController.class).retrieve(entity.getUserId())), "retrieve-contact-person-number", HttpMethod.GET)
            );
        else if(Objects.equals(PhonePurpose.CONTACT_PERSON_EMERGENCY, entity.getPurpose()))
            return EntityModel.of(entity,
                    link(linkTo(methodOn(ContactPersonPhoneController.class).set(entity.getUserId(), null)), "set-contact-person-emergency-number", HttpMethod.PUT),
                    link(linkTo(methodOn(ContactPersonPhoneController.class).verify(entity.getUserId())), "verify-contact-person-emergency-number", HttpMethod.PUT),
                    link(linkTo(methodOn(ContactPersonPhoneController.class).retrieve(entity.getUserId())), "retrieve-contact-person-emergency-number", HttpMethod.GET)
            );

        return EntityModel.of(new PhoneDto());
    }

    @Override
    public CollectionModel<EntityModel<PhoneDto>> toCollectionModel(Iterable<? extends PhoneDto> entities) {
        List<EntityModel<PhoneDto>> entityModels = StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toModel).toList();

        Long userId = StreamSupport.stream(entities.spliterator(), false)
                .map(PhoneDto::getUserId).findFirst().orElse(null);

        return CollectionModel.of(entityModels,
                link(linkTo(methodOn(PhoneController.class).retrieveAll(userId)), "retrieve-all-phones", HttpMethod.GET)
        );
    }

}