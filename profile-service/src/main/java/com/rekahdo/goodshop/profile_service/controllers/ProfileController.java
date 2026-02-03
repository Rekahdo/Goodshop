package com.rekahdo.goodshop.profile_service.controllers;

import com.rekahdo.goodshop.profile_service.dtos.entities.ProfileDto;
import com.rekahdo.goodshop.profile_service.dtos.request.DOBRequest;
import com.rekahdo.goodshop.profile_service.dtos.request.ProfileRequest;
import com.rekahdo.goodshop.profile_service.enums.Gender;
import com.rekahdo.goodshop.profile_service.feign.clients.PhoneServiceClient;
import com.rekahdo.goodshop.profile_service.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.profile_service.feign.dtos.PhoneRequest;
import com.rekahdo.goodshop.profile_service.feign.enums.Purpose;
import com.rekahdo.goodshop.profile_service.services.ProfileService;
import com.rekahdo.goodshop.profile_service.utilities.ApiKey;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/profile")
@RequiredArgsConstructor
@EnableMethodSecurity
public class ProfileController {

    private final ProfileService service;
    private final UserServiceClient userService;
    private final PhoneServiceClient phoneService;

    @GetMapping(path = "/retrieve")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("#userId == authentication.principal.userId OR hasRole('ADMIN')")
    public ProfileDto retrieve(@RequestParam Long userId){
        userService.validate(userId, ApiKey.USER_SERVICE);
        return service.retrieve(userId);
    }

    @PostMapping(path = "/set-names")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("#userId == authentication.principal.userId")
    public void setNames(@RequestParam Long userId,
                         @Valid @RequestBody ProfileRequest request){
        userService.validate(userId, ApiKey.USER_SERVICE);
        service.setNames(userId, request);
    }

    @PostMapping(path = "/set-date-of-birth")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("#userId == authentication.principal.userId")
    public void setDob(@RequestParam Long userId,
                       @Valid @RequestBody DOBRequest request){
        userService.validate(userId, ApiKey.USER_SERVICE);
        service.setDob(userId, request);
    }

    @PostMapping(path = "/set-gender")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("#userId == authentication.principal.userId")
    public void setGender(@RequestParam Long userId,
                          @Valid @RequestParam Gender gender){
        userService.validate(userId, ApiKey.USER_SERVICE);
        service.setGender(userId, gender);
    }

}
