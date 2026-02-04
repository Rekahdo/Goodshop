package com.rekahdo.goodshop.profile_service.services;

import com.rekahdo.goodshop.profile_service.controllers.ProfileController;
import com.rekahdo.goodshop.profile_service.mappers.ProfileMapper;
import com.rekahdo.goodshop.profile_service.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class HiddenService {

    private final ProfileRepository repository;
    private final ProfileMapper mapper;

    public Map<String, URI> retrieveURIs(Long userId) {
        return Map.of(
                "profile", linkTo(methodOn(ProfileController.class).retrieve(userId)).toUri()
        );
    }

}
