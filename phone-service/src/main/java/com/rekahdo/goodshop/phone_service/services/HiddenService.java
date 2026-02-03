package com.rekahdo.goodshop.phone_service.services;

import com.rekahdo.goodshop.phone_service.controllers.BusinessPhoneController;
import com.rekahdo.goodshop.phone_service.controllers.ContactPersonEmergencyPhoneController;
import com.rekahdo.goodshop.phone_service.controllers.ContactPersonPhoneController;
import com.rekahdo.goodshop.phone_service.controllers.ProfilePhoneController;
import com.rekahdo.goodshop.phone_service.dtos.clients.PhoneClient;
import com.rekahdo.goodshop.phone_service.entities.Phone;
import com.rekahdo.goodshop.phone_service.enums.PhonePurpose;
import com.rekahdo.goodshop.phone_service.exceptions.classes.PhoneNotFoundException;
import com.rekahdo.goodshop.phone_service.mappers.PhoneMapper;
import com.rekahdo.goodshop.phone_service.repositories.PhoneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class HiddenService {

    private final PhoneRepository repository;
    private final PhoneMapper mapper;

    private final DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String serviceName;

    public Map<String, URI> retrieveURIs(Long userId) {
        return Map.of(
                "profile", linkTo(methodOn(ProfilePhoneController.class).retrieve(userId)).toUri(),
                "business", linkTo(methodOn(BusinessPhoneController.class).retrieve(userId)).toUri(),
                "contact-person", linkTo(methodOn(ContactPersonPhoneController.class).retrieve(userId)).toUri(),
                "contact-person-emergency", linkTo(methodOn(ContactPersonEmergencyPhoneController.class).retrieve(userId)).toUri()
        );
    }

    public PhoneClient findAndThrow(Long userId, PhonePurpose purpose) {
        Phone phone = repository.findByUserIdAndPurpose(userId, purpose.index)
                .orElseThrow(() -> new PhoneNotFoundException(userId));

        return mapper.toClient(phone);
    }

    public PhoneClient find(Long userId, PhonePurpose purpose) {
        return repository.findByUserIdAndPurpose(userId, purpose.index)
                .map(mapper::toClient).orElse(null);
    }

    public List<PhoneClient> retrieveAll(Long userId, List<PhonePurpose> purposes) {
        List<Phone> phones = repository.findByUserIdAndPurposeIn(userId,
                purposes.stream().map(PhonePurpose::getIndex).toList());

        if(phones.isEmpty()) throw new PhoneNotFoundException(userId);

        return mapper.toClientList(phones);
    }

    public void delete(Long userId, PhonePurpose purpose) {
        repository.deleteByUserIdAndPurpose(userId, purpose.index);
    }

    public void deleteAll(Long userId, List<PhonePurpose> purposes) {
        repository.deleteByUserIdAndPurposeIn(userId,
                purposes.stream().map(PhonePurpose::getIndex).toList());
    }

    public PhoneClient retrieveByUserIdAndNumber(Long userId, String countryCode, String number) {
        Phone phone = repository.findByUserIdAndCode_NumberAndNumber(userId, countryCode, number)
                .orElseThrow(() -> new PhoneNotFoundException(userId, number));

        return mapper.toClient(phone);
    }

}
