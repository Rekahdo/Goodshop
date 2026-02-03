package com.rekahdo.goodshop.address_service.services;

import com.rekahdo.goodshop.address_service.controllers.AddressController;
import com.rekahdo.goodshop.address_service.controllers.BankAddressController;
import com.rekahdo.goodshop.address_service.controllers.BusinessAddressController;
import com.rekahdo.goodshop.address_service.controllers.ProfileAddressController;
import com.rekahdo.goodshop.address_service.dtos.clients.AddressClient;
import com.rekahdo.goodshop.address_service.entities.Address;
import com.rekahdo.goodshop.address_service.enums.AddressPurpose;
import com.rekahdo.goodshop.address_service.exceptions.classes.AddressNotFoundException;
import com.rekahdo.goodshop.address_service.mappers.AddressMapper;
import com.rekahdo.goodshop.address_service.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
@Slf4j
public class HiddenService {

    private final AddressRepository repository;
    private final AddressMapper mapper;

    public Map<String, URI> retrieveURIs(Long userId) {
        return Map.of(
                "profile", linkTo(methodOn(ProfileAddressController.class).retrieve(userId)).toUri(),
                "business", linkTo(methodOn(BusinessAddressController.class).retrieve(userId)).toUri(),
                "bank", linkTo(methodOn(BankAddressController.class).retrieve(userId)).toUri(),
                "addresses", linkTo(methodOn(AddressController.class).retrieveAll(userId)).toUri()
        );
    }

    public AddressClient getClient(Long id) {
        Address address = repository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException(id));

        return mapper.toClient(address);
    }

    public AddressClient find(Long userId, AddressPurpose purpose) {
        return repository.findByUserIdAndPurpose(userId, purpose.index)
                .map(mapper::toClient).orElse(null);
    }

}
