package com.rekahdo.goodshop.address_service.controllers;

import com.rekahdo.goodshop.address_service.dtos.entities.AddressDto;
import com.rekahdo.goodshop.address_service.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/addresses")
@RequiredArgsConstructor
@EnableMethodSecurity
public class AddressController {

	private final AddressService service;

	@GetMapping("/retrieve-all")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#userId == authentication.principal.userId OR hasRole('ADMIN')")
	public CollectionModel<EntityModel<AddressDto>> retrieveAll(@RequestParam Long userId){
		return service.retrieveAll(userId);
	}

}