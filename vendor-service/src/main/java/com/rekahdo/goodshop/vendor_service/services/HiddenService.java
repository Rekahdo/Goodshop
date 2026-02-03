package com.rekahdo.goodshop.vendor_service.services;

import com.rekahdo.goodshop.vendor_service.controllers.VendorController;
import com.rekahdo.goodshop.vendor_service.exceptions.classes.VendorNotFoundException;
import com.rekahdo.goodshop.vendor_service.mappers.VendorMapper;
import com.rekahdo.goodshop.vendor_service.repositories.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class HiddenService {

    private final VendorRepository repository;
    private final VendorMapper mapper;

    public Map<String, URI> retrieveURIs(Long userId) {
        return Map.of(
                "vendor", linkTo(methodOn(VendorController.class).retrieve(userId)).toUri(),
                "all-vendor", linkTo(methodOn(VendorController.class).retrieveAll(null)).toUri(),
                "unresolved", linkTo(methodOn(VendorController.class).getUnresolved(userId)).toUri()
        );
    }

    public void vendorExistence(Long userId) {
        if(!repository.existsByUserId(userId)){
            throw new VendorNotFoundException(userId);
        }
    }

}
