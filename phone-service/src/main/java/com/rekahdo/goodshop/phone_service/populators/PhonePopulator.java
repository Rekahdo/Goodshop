package com.rekahdo.goodshop.phone_service.populators;

import com.rekahdo.goodshop.phone_service.repositories.PhoneRepository;
import com.rekahdo.goodshop.phone_service.services.PhoneService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@RequiredArgsConstructor
public class PhonePopulator {

    private final PhoneRepository repository;
    private final PhoneService service;

    @PostConstruct
    private void insert() {
        if (!repository.findAll().isEmpty())
            return;

//        service.add(2L, PhonePurpose.PROFILE, new PhoneRequest("234", "9059405621"));
//        service.add(2L, PhonePurpose.PROFILE, new PhoneRequest("234", "9097114626"));
//        service.add(2L, PhonePurpose.PROFILE, new PhoneRequest("234", "9030340615"));
//
//        service.add(3L, PhonePurpose.PROFILE, new PhoneRequest("234", "9030340615"));
    }

}
