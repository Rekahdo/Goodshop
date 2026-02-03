package com.rekahdo.goodshop.address_service.services;

import com.rekahdo.goodshop.address_service.dtos.request.AddressRequest;
import com.rekahdo.goodshop.address_service.entities.LGA;
import com.rekahdo.goodshop.address_service.repositories.LGARepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LGAService {

    private final LGARepository repository;

    public LGA add(AddressRequest record) {
        if(record.lga() == null)
            return null;

        LGA entity = repository.findByNameIgnoreCase(record.lga())
                .orElseGet(LGA::new);

        entity.setName(StringUtils.capitalize(record.lga()));
        repository.save(entity);
        return entity;
    }

}
