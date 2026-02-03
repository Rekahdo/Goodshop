package com.rekahdo.goodshop.forgot_password.controllers;

import com.rekahdo.goodshop.forgot_password.services.HiddenService;
import com.rekahdo.goodshop.forgot_password.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(path = "/dev-hide/1060/forgot-password")
@RequiredArgsConstructor
public class HiddenController {

	private final HiddenService service;

	@GetMapping(path = "/retrieve-uris", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, URI> retrieveURIs(@RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.retrieveURIs();
	}


}