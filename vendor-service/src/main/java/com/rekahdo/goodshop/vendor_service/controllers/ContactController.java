package com.rekahdo.goodshop.vendor_service.controllers;

import com.rekahdo.goodshop.vendor_service.dtos.request.ContactRequest;
import com.rekahdo.goodshop.vendor_service.services.ContactService;
import com.rekahdo.goodshop.vendor_service.services.HiddenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/vendors/contact-person")
@RequiredArgsConstructor
@EnableMethodSecurity
public class ContactController {

	private final ContactService service;
	private final HiddenService hiddenService;

	@PutMapping(path = "/set", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#userId == authentication.principal.userId")
	public void set(@RequestParam Long userId, @RequestBody @Valid ContactRequest request) {
		hiddenService.vendorExistence(userId);
		service.set(userId, request);
	}

}