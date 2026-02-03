package com.rekahdo.goodshop.address_service.services;

import com.rekahdo.goodshop.address_service.dtos.assemblers.AddressAssembler;
import com.rekahdo.goodshop.address_service.dtos.entities.AddressDto;
import com.rekahdo.goodshop.address_service.dtos.request.AddressRequest;
import com.rekahdo.goodshop.address_service.entities.*;
import com.rekahdo.goodshop.address_service.enums.AddressPurpose;
import com.rekahdo.goodshop.address_service.exceptions.classes.AddressNotFoundException;
import com.rekahdo.goodshop.address_service.feign.clients.VendorServiceClient;
import com.rekahdo.goodshop.address_service.feign.enums.UnresolvedReason;
import com.rekahdo.goodshop.address_service.mappers.AddressMapper;
import com.rekahdo.goodshop.address_service.repositories.*;
import com.rekahdo.goodshop.address_service.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressService {

    private final AddressRepository repository;
    private final AddressMapper mapper;
    private final AddressAssembler assembler;

    private final StreetService streetService;
    private final BusStopService busStopService;
    private final LGAService lgaService;
    private final CityService cityService;
    private final StateService stateService;
    private final CountryService countryService;
    private final ZipcodeService zipcodeService;

    private final VendorServiceClient vendorService;

    private Address find(Long userId, AddressPurpose purpose){
        return repository.findByUserIdAndPurpose(userId, purpose.index)
                .orElse(new Address(userId, purpose));
    }

    public ResponseEntity<Long> set(Long userId, AddressPurpose purpose, AddressRequest request) {
        Address address = find(userId, purpose);
        address.setStreet(streetService.add(request));
        address.setOffStreet(streetService.addOffStreet(request));
        address.setBusStop(busStopService.add(request));
        address.setLga(lgaService.add(request));
        address.setCity(cityService.add(request));
        address.setState(stateService.add(request));
        address.setCountry(countryService.add(request));
        address.setZipcode(zipcodeService.add(request));
        mapper.updateEntity(request, address);

        if(address.getId() == null){
            if(Objects.equals(AddressPurpose.BUSINESS, purpose)) {
                vendorService.businessAddressAdded(userId, ApiKey.VENDOR_SERVICE);
                vendorService.deleteUnresolved(userId, UnresolvedReason.BUSINESS_ADDRESS_NOT_PROVIDED, ApiKey.VENDOR_SERVICE);
            }
            else if(Objects.equals(AddressPurpose.BANK, purpose)) {
                vendorService.bankAddressAdded(userId, ApiKey.VENDOR_SERVICE);
                vendorService.deleteUnresolved(userId, UnresolvedReason.BANK_ADDRESS_NOT_PROVIDED, ApiKey.VENDOR_SERVICE);
            }
        }

        repository.save(address);
        return ResponseEntity.ok().body(address.getId());
    }

    public EntityModel<AddressDto> retrieve(Long userId, AddressPurpose purpose) {
        Address address = repository.findByUserIdAndPurpose(userId, purpose.index)
                .orElseThrow(() -> new AddressNotFoundException(userId, purpose));

        AddressDto dto = mapper.toDto(address);
        return assembler.toModel(dto);
    }

    public CollectionModel<EntityModel<AddressDto>> retrieveAll(Long userId) {
        List<Address> addresses = repository.findByUserId(userId);

        if(addresses.isEmpty())
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);

        return assembler.toCollectionModel(mapper.toDtoList(addresses));
    }

}
