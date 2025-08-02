package com.rekahdo.ecommerce.goodshop._services;

import com.rekahdo.ecommerce.goodshop._dtos.entities.AddressDto;
import com.rekahdo.ecommerce.goodshop._entities.Address;
import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import com.rekahdo.ecommerce.goodshop._mappers.AddressMapper;
import com.rekahdo.ecommerce.goodshop._repository.AddressRepository;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.AddressNotFoundException;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.EmptyListException;
import com.rekahdo.ecommerce.goodshop.mappingJacksonValue.AddressMJV;
import com.rekahdo.ecommerce.goodshop.utilities.ApiLogger;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AddressService {

    @Autowired
    private AddressRepository repository;

    @Autowired
    private AddressMapper mapper;

    @Autowired
    private AddressMJV mjv;

    private final ApiLogger logger = new ApiLogger(getClass());

    public ResponseEntity<?> addAddress(Long userId, AddressDto dto) {
        Address address = mapper.toEntity(dto);
        address.setAppUser(new AppUser(userId));
        repository.save(address);
        logger.log("User address has been saved");
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editAddress(Long userId, Long addressId, AddressDto dto) {
        Optional<Address> optional = repository.findByIdAndAppUserId(addressId, userId);
        if(optional.isEmpty()) throw new AddressNotFoundException();

        Address address = optional.get();
        mapper.updateEntity(dto, address);
        repository.save(address);
        logger.log("User address has been modified");
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getAddresses(Long userId) {
        List<Address> addresses = repository.findAllByAppUserId(userId);
        if(addresses.isEmpty()) throw new EmptyListException();

        List<AddressDto> dtos = mapper.toDtoList(addresses);
        return ResponseEntity.ok(mjv.selfFilter(dtos));
    }

    public ResponseEntity<?> getAddress(Long userId, Long addressId) {
        Optional<Address> optional = repository.findByIdAndAppUserId(addressId, userId);
        if(optional.isEmpty()) throw new AddressNotFoundException();

        AddressDto dto = mapper.toDto(optional.get());
        return ResponseEntity.ok(mjv.selfFilter(dto));
    }

    public ResponseEntity<?> deleteAddress(Long userId, Long addressId) {
        repository.deleteByIdAndAppUserId(addressId, userId);
        return ResponseEntity.ok().build();
    }
}
