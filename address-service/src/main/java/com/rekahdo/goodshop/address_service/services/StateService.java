package com.rekahdo.goodshop.address_service.services;

import com.rekahdo.goodshop.address_service.dtos.request.AddressRequest;
import com.rekahdo.goodshop.address_service.entities.State;
import com.rekahdo.goodshop.address_service.repositories.StateRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository repository;

    public State add(AddressRequest record) {
        if(record.state() == null)
            return null;

        State entity = repository.findByNameIgnoreCase(record.state())
                .orElseGet(State::new);

        entity.setName(StringUtils.capitalize(record.state()));
        repository.save(entity);
        return entity;
    }

}
