package com.rekahdo.goodshop.vendor_service.dtos.paginations;

import com.rekahdo.goodshop.vendor_service.controllers.VendorController;
import com.rekahdo.goodshop.vendor_service.dtos.entities.VendorDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public final class VendorPageRequest extends ApiPageRequest<VendorDto> {

    public VendorPageRequest() {
        super(methodOn(VendorController.class).retrieveAll(null));
    }

}