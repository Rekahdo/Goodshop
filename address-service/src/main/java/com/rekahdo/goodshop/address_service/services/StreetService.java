package com.rekahdo.goodshop.address_service.services;

import com.rekahdo.goodshop.address_service.dtos.request.AddressRequest;
import com.rekahdo.goodshop.address_service.entities.Street;
import com.rekahdo.goodshop.address_service.repositories.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StreetService {

    private final StreetRepository repository;

    public Street add(AddressRequest record) {
        if(record.street() == null)
            return null;

        Street entity = repository.findByNameIgnoreCase(record.street())
                .orElseGet(Street::new);

        entity.setName(StringUtils.capitalize(record.street()));
        repository.save(entity);
        return entity;
    }

    public Street addOffStreet(AddressRequest record) {
        if(record.offStreet() == null)
            return null;

        Street entity = repository.findByNameIgnoreCase(record.offStreet())
                .orElseGet(Street::new);

        entity.setName(StringUtils.capitalize(record.offStreet()));
        repository.save(entity);
        return entity;
    }

}
