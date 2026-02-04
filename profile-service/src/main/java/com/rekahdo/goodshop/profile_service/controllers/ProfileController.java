package com.rekahdo.goodshop.profile_service.controllers;

import com.rekahdo.goodshop.profile_service.dtos.entities.ProfileDto;
import com.rekahdo.goodshop.profile_service.dtos.request.DOBRequest;
import com.rekahdo.goodshop.profile_service.dtos.request.ProfileRequest;
import com.rekahdo.goodshop.profile_service.enums.Gender;
import com.rekahdo.goodshop.profile_service.services.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/profile")
@RequiredArgsConstructor
@EnableMethodSecurity
@Validated
public class ProfileController {

    private final ProfileService service;

    @GetMapping(path = "/retrieve", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("#userId == authentication.principal.userId OR hasRole('ADMIN')")
    public ResponseEntity<EntityModel<ProfileDto>> retrieve(@RequestParam Long userId){
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.retrieve(userId));
    }

    @PutMapping(path = "/set-names", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<Void> setNames(@RequestParam Long userId,
                         @Valid @RequestBody ProfileRequest request){
        service.setNames(userId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/set-date-of-birth", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<Void> setDob(@RequestParam Long userId,
                       @Valid @RequestBody DOBRequest request){
        service.setDob(userId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping(path = "/set-gender")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<Void> setGender(@RequestParam Long userId,
                          @RequestParam Gender gender){
        service.setGender(userId, gender);
        return ResponseEntity.ok().build();
    }

}
