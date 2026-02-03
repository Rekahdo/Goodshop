package com.rekahdo.goodshop.address_service.controllers;

import com.rekahdo.goodshop.address_service.dtos.clients.AddressClient;
import com.rekahdo.goodshop.address_service.enums.AddressPurpose;
import com.rekahdo.goodshop.address_service.services.HiddenService;
import com.rekahdo.goodshop.address_service.utilities.ApiKey;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(path = "/dev-hide/1090/addresses")
@RequiredArgsConstructor
@Hidden
public class HiddenController {

	private final HiddenService service;

	@GetMapping(path = "/retrieve-uris", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, URI> retrieveURIs(@RequestParam Long userId,
										 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.retrieveURIs(userId);
	}

	@GetMapping("/retrieve-client")
	@ResponseStatus(HttpStatus.OK)
	public AddressClient getClient(@RequestParam Long userId,
								   @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.getClient(userId);
	}

	@GetMapping(path = "/retrieve-business-address", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public AddressClient retrieveBusiness(@RequestParam Long userId,
										  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.find(userId, AddressPurpose.BUSINESS);
	}

	@GetMapping(path = "/retrieve-bank-address", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public AddressClient retrieveBank(@RequestParam Long userId,
									  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.find(userId, AddressPurpose.BANK);
	}

}