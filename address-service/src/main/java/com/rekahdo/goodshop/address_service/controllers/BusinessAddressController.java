package com.rekahdo.goodshop.address_service.controllers;

import com.rekahdo.goodshop.address_service.dtos.entities.AddressDto;
import com.rekahdo.goodshop.address_service.dtos.request.AddressRequest;
import com.rekahdo.goodshop.address_service.enums.AddressPurpose;
import com.rekahdo.goodshop.address_service.feign.clients.VendorServiceClient;
import com.rekahdo.goodshop.address_service.services.AddressService;
import com.rekahdo.goodshop.address_service.utilities.ApiKey;
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
@RequestMapping(path = "/api/v1/addresses")
@RequiredArgsConstructor
@EnableMethodSecurity
public class BusinessAddressController {

	private final AddressService service;
	private final VendorServiceClient vendorService;

	// BUSINESS
	@PutMapping(path = "/set-for-business", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("#userId == authentication.principal.userId")
	public ResponseEntity<Void> set(@RequestParam Long userId, @Valid @RequestBody AddressRequest request){
		vendorService.validateExistence(userId, ApiKey.VENDOR_SERVICE);
		service.set(userId, AddressPurpose.BUSINESS, request);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/retrieve-for-business")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#userId == authentication.principal.userId OR hasRole('ADMIN')")
	public EntityModel<AddressDto> retrieve(@RequestParam Long userId){
		return service.retrieve(userId, AddressPurpose.BUSINESS);
	}

}