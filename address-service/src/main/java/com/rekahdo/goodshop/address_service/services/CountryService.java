package com.rekahdo.goodshop.address_service.services;

import com.rekahdo.goodshop.address_service.dtos.request.AddressRequest;
import com.rekahdo.goodshop.address_service.entities.Country;
import com.rekahdo.goodshop.address_service.repositories.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository repository;

    public Country add(AddressRequest record) {
        if(record.country() == null)
            return null;

        Country entity = repository.findByNameIgnoreCase(record.country())
                .orElseGet(Country::new);

        entity.setName(StringUtils.capitalize(record.country()));
        repository.save(entity);
        return entity;
    }

}
