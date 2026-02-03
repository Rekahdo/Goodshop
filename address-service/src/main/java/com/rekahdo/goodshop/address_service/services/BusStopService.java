package com.rekahdo.goodshop.address_service.services;

import com.rekahdo.goodshop.address_service.dtos.request.AddressRequest;
import com.rekahdo.goodshop.address_service.entities.BusStop;
import com.rekahdo.goodshop.address_service.entities.Street;
import com.rekahdo.goodshop.address_service.repositories.BusStopRepository;
import com.rekahdo.goodshop.address_service.repositories.StreetRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusStopService {

    private final BusStopRepository repository;

    public BusStop add(AddressRequest record) {
        if(record.closestBusStop() == null)
            return null;

        BusStop entity = repository.findByNameIgnoreCase(record.closestBusStop())
                .orElseGet(BusStop::new);

        entity.setName(StringUtils.capitalize(record.closestBusStop()));
        repository.save(entity);
        return entity;
    }

}
