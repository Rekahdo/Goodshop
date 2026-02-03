package com.rekahdo.goodshop.address_service.services;

import com.rekahdo.goodshop.address_service.dtos.request.AddressRequest;
import com.rekahdo.goodshop.address_service.entities.City;
import com.rekahdo.goodshop.address_service.repositories.CityRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository repository;

    public City add(AddressRequest record) {
        if(record.city() == null)
            return null;

        City entity = repository.findByNameIgnoreCase(record.city())
                .orElseGet(City::new);

        entity.setName(StringUtils.capitalize(record.city()));
        repository.save(entity);
        return entity;
    }

}
