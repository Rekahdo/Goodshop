package com.rekahdo.goodshop.phone_service.controllers;

import com.rekahdo.goodshop.phone_service.dtos.entities.PhoneDto;
import com.rekahdo.goodshop.phone_service.dtos.request.PhoneRequest;
import com.rekahdo.goodshop.phone_service.enums.PhonePurpose;
import com.rekahdo.goodshop.phone_service.feign.clients.VendorServiceClient;
import com.rekahdo.goodshop.phone_service.services.PhoneService;
import com.rekahdo.goodshop.phone_service.utilities.ApiKey;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/phones")
@RequiredArgsConstructor
@EnableMethodSecurity
public class BusinessPhoneController {

	private final PhoneService service;
	private final VendorServiceClient vendorService;

	@PutMapping(path = "/set-business-number")
	@PreAuthorize("#userId == authentication.principal.userId")
	public ResponseEntity<Void> set(@RequestParam Long userId,
									@Valid @RequestBody PhoneRequest request){
		vendorService.validateExistence(userId, ApiKey.VENDOR_SERVICE);
		service.set(userId, PhonePurpose.BUSINESS, request);
		return ResponseEntity.accepted().build();
	}

	@PutMapping(path = "/verify-business-number")
	@PreAuthorize("#userId == authentication.principal.userId")
	public ResponseEntity<Void> verify(@RequestParam Long userId){
		service.verify(userId, PhonePurpose.BUSINESS);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/retrieve-business-number")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#userId == authentication.principal.userId")
	public EntityModel<PhoneDto> retrieve(@RequestParam Long userId){
		return service.retrieve(userId, PhonePurpose.BUSINESS);
	}

}