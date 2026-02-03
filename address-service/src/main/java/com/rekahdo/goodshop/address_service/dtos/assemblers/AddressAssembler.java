package com.rekahdo.goodshop.address_service.dtos.assemblers;

import com.rekahdo.goodshop.address_service.controllers.AddressController;
import com.rekahdo.goodshop.address_service.controllers.BankAddressController;
import com.rekahdo.goodshop.address_service.controllers.BusinessAddressController;
import com.rekahdo.goodshop.address_service.controllers.ProfileAddressController;
import com.rekahdo.goodshop.address_service.dtos.entities.AddressDto;
import com.rekahdo.goodshop.address_service.enums.AddressPurpose;
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
public class AddressAssembler extends ApiAssembler<AddressDto> {

    @Override
    public EntityModel<AddressDto> toModel(AddressDto entity) {
        if(Objects.equals(AddressPurpose.PROFILE, entity.getPurpose()))
            return EntityModel.of(entity,
                    link(linkTo(methodOn(ProfileAddressController.class).set(entity.getUserId(), null)), "set-profile", HttpMethod.PUT),
                    link(linkTo(methodOn(ProfileAddressController.class).retrieve(entity.getUserId())), HttpMethod.GET)
            );
        else if(Objects.equals(AddressPurpose.BUSINESS, entity.getPurpose()))
            return EntityModel.of(entity,
                    link(linkTo(methodOn(BusinessAddressController.class).set(entity.getUserId(), null)), "set-profile", HttpMethod.PUT),
                    link(linkTo(methodOn(BusinessAddressController.class).retrieve(entity.getUserId())), HttpMethod.GET)
            );
        else if(Objects.equals(AddressPurpose.BANK, entity.getPurpose()))
            return EntityModel.of(entity,
                    link(linkTo(methodOn(BankAddressController.class).set(entity.getUserId(), null)), "set-profile", HttpMethod.PUT),
                    link(linkTo(methodOn(BankAddressController.class).retrieve(entity.getUserId())), "retrieve-profile", HttpMethod.GET)
            );
        else
            return EntityModel.of(entity);
    }

    @Override
    public CollectionModel<EntityModel<AddressDto>> toCollectionModel(Iterable<? extends AddressDto> entities) {
        List<EntityModel<AddressDto>> entityModels = StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toModel).toList();

        Long userId = StreamSupport.stream(entities.spliterator(), false)
                .map(AddressDto::getUserId).findFirst().orElse(null);

        return CollectionModel.of(entityModels,
                link(linkTo(methodOn(AddressController.class).retrieveAll(userId)), "retrieve-all-address", HttpMethod.GET)
        );
    }

}