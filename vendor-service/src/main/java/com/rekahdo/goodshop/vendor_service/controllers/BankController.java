package com.rekahdo.goodshop.vendor_service.controllers;

import com.rekahdo.goodshop.vendor_service.dtos.request.BankRequest;
import com.rekahdo.goodshop.vendor_service.services.BankService;
import com.rekahdo.goodshop.vendor_service.services.HiddenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/vendors/bank-account-details")
@RequiredArgsConstructor
@EnableMethodSecurity
public class BankController {

	private final BankService service;
	private final HiddenService hiddenService;

	@PutMapping(path = "/set", consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	@PreAuthorize("#userId == authentication.principal.userId")
	public Long set(@RequestParam Long userId, @RequestBody @Valid BankRequest request){
		hiddenService.vendorExistence(userId);
		return service.set(userId, request);
	}

}