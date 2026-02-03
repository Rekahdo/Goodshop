package com.rekahdo.goodshop.profile_service.services;

import com.rekahdo.goodshop.profile_service.controllers.ProfileController;
import com.rekahdo.goodshop.profile_service.dtos.entities.ProfileDto;
import com.rekahdo.goodshop.profile_service.dtos.request.DOBRequest;
import com.rekahdo.goodshop.profile_service.dtos.request.ProfileRequest;
import com.rekahdo.goodshop.profile_service.entities.Profile;
import com.rekahdo.goodshop.profile_service.enums.Gender;
import com.rekahdo.goodshop.profile_service.feign.clients.PhoneServiceClient;
import com.rekahdo.goodshop.profile_service.mappers.ProfileMapper;
import com.rekahdo.goodshop.profile_service.repositories.ProfileRepository;
import com.rekahdo.goodshop.profile_service.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDate;
import java.time.Period;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class HiddenService {

    private final ProfileRepository repository;
    private final ProfileMapper mapper;

    private final PhoneServiceClient phoneService;

    public Map<String, URI> retrieveURIs(Long userId) {
        return Map.of(
                "profile", linkTo(methodOn(ProfileController.class).retrieve(userId)).toUri()
        );
    }

}
