package com.rekahdo.goodshop.phone_service.controllers;

import com.rekahdo.goodshop.phone_service.dtos.entities.PhoneDto;
import com.rekahdo.goodshop.phone_service.dtos.request.PhoneRequest;
import com.rekahdo.goodshop.phone_service.enums.PhonePurpose;
import com.rekahdo.goodshop.phone_service.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.phone_service.services.PhoneService;
import com.rekahdo.goodshop.phone_service.utilities.ApiKey;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/phones")
@RequiredArgsConstructor
@EnableMethodSecurity
public class ProfilePhoneController {

	private final PhoneService service;
	private final UserServiceClient userService;

	// PROFILE
	@PutMapping(path = "/set-profile-number")
	@PreAuthorize("#userId == authentication.principal.userId")
	public ResponseEntity<Void> set(@RequestParam Long userId,
							  @Valid @RequestBody PhoneRequest request){
		userService.validate(userId, ApiKey.USER_SERVICE);
		service.set(userId, PhonePurpose.PROFILE, request);
		return ResponseEntity.accepted().build();
	}

	@PutMapping(path = "/verify-profile-number")
	@PreAuthorize("#userId == authentication.principal.userId")
	public ResponseEntity<Void> verify(@RequestParam Long userId){
		service.verify(userId, PhonePurpose.PROFILE);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/retrieve-profile-number", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#userId == authentication.principal.userId")
	public EntityModel<PhoneDto> retrieve(@RequestParam Long userId){
		return service.retrieve(userId, PhonePurpose.PROFILE);
	}

}