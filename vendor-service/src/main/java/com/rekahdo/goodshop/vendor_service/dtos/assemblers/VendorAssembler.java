package com.rekahdo.goodshop.vendor_service.dtos.assemblers;

import com.rekahdo.goodshop.vendor_service.controllers.VendorController;
import com.rekahdo.goodshop.vendor_service.dtos.entities.VendorDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class VendorAssembler extends ApiAssembler<VendorDto> {

    public VendorAssembler() {
        super(methodOn(VendorController.class).retrieveAll(null));
    }

    @Override
    public EntityModel<VendorDto> toModel(VendorDto entity) {
        return EntityModel.of(entity,
                link(linkTo(methodOn(VendorController.class).retrieve(entity.getUserId())), HttpMethod.GET)
        );
    }

    @Override
    public CollectionModel<EntityModel<VendorDto>> toCollectionModel(Iterable<? extends VendorDto> entities) {
        List<EntityModel<VendorDto>> entityModels = StreamSupport
                .stream(entities.spliterator(), false)
                .map(this::toModel).toList();

        return CollectionModel.of(entityModels,
                link(linkTo(methodOn(VendorController.class).retrieveAll(null)), HttpMethod.GET)
        );
    }

}