package com.rekahdo.goodshop.phone_service.controllers;

import com.rekahdo.goodshop.phone_service.dtos.entities.PhoneDto;
import com.rekahdo.goodshop.phone_service.services.PhoneService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/phones")
@RequiredArgsConstructor
@EnableMethodSecurity
public class PhoneController {

	private final PhoneService service;

	@GetMapping(path = "/retrieve-all")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#userId == authentication.principal.userId OR hasRole('ADMIN') OR hasRole('MODERATOR')")
	public CollectionModel<EntityModel<PhoneDto>> retrieveAll(@RequestParam Long userId){
		return service.retrieveAll(userId);
	}

}