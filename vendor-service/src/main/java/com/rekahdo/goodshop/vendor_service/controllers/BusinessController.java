package com.rekahdo.goodshop.vendor_service.controllers;

import com.rekahdo.goodshop.vendor_service.dtos.request.BusinessRequest;
import com.rekahdo.goodshop.vendor_service.services.BusinessService;
import com.rekahdo.goodshop.vendor_service.services.HiddenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/vendors/business")
@RequiredArgsConstructor
@EnableMethodSecurity
public class BusinessController {

	private final BusinessService service;
	private final HiddenService hiddenService;

	@PutMapping(path = "/set", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#userId == authentication.principal.userId")
	public void set(@RequestParam Long userId, @RequestBody @Valid BusinessRequest request) {
		hiddenService.vendorExistence(userId);
		service.set(userId, request);
	}

	@PutMapping(path = "/verify")
	@PreAuthorize("#userId == authentication.principal.userId")
	public ResponseEntity<Void> verify(@RequestParam Long userId){
		service.verify(userId);
		return ResponseEntity.ok().build();
	}

}