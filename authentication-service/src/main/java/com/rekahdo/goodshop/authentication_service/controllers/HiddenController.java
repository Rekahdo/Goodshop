package com.rekahdo.goodshop.authentication_service.controllers;

import com.rekahdo.goodshop.authentication_service.dtos.response.JwtResponse;
import com.rekahdo.goodshop.authentication_service.services.HiddenService;
import com.rekahdo.goodshop.authentication_service.utilities.ApiKey;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/dev-hide/1030/authentication")
@RequiredArgsConstructor
@Hidden
public class HiddenController {

	private final HiddenService service;

	@PostMapping(path = "/token/generate")
	@ResponseStatus(HttpStatus.OK)
	public JwtResponse generateToken(@RequestParam String username, @RequestParam List<String> roles,
									 @RequestParam Map<String, ?> claims, @RequestParam Integer expireInHours,
									 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.generateToken(username, roles, claims, expireInHours);
	}

	@PostMapping(path = "/token/generate-for-service")
	@ResponseStatus(HttpStatus.OK)
	public String generateTokenForService(@RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.generateToken().token();
	}

	@PostMapping(path = "/token/retrieve-info")
	@ResponseStatus(HttpStatus.OK)
	public ModelMap tokenInfo(@RequestParam String token,
							  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.tokenInfo(token);
	}

}