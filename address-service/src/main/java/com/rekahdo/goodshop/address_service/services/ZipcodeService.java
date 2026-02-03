package com.rekahdo.goodshop.address_service.services;

import com.rekahdo.goodshop.address_service.dtos.request.AddressRequest;
import com.rekahdo.goodshop.address_service.entities.Zipcode;
import com.rekahdo.goodshop.address_service.repositories.ZipcodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ZipcodeService {

    private final ZipcodeRepository repository;

    public Zipcode add(AddressRequest record) {
        if(record.zipcode() == null)
            return null;

        Zipcode entity = repository.findByCode(record.zipcode())
                .orElseGet(Zipcode::new);

        entity.setCode(record.zipcode());
        repository.save(entity);
        return entity;
    }

}
