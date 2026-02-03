package com.rekahdo.goodshop.user_service.controllers;

import com.rekahdo.goodshop.user_service.dtos.clients.AppUserClient;
import com.rekahdo.goodshop.user_service.dtos.request.Login;
import com.rekahdo.goodshop.user_service.feign.dtos.JwtResponse;
import com.rekahdo.goodshop.user_service.services.HiddenService;
import com.rekahdo.goodshop.user_service.utilities.ApiKey;
import com.rekahdo.goodshop.user_service.validations.annotations.StrongPassword;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(path = "/dev-hide/1020/users")
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

	@PostMapping(path = "/login")
	@ResponseStatus(HttpStatus.OK)
	public JwtResponse login(@Valid @RequestBody Login request,
							 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.login(request);
	}

	// USED BY FORGOT PASSWORD SERVICE
	@PutMapping(path = "/reset-password")
	@ResponseStatus(HttpStatus.OK)
	public void resetPassword(@Valid @RequestParam @Email String email,
							  @Valid @StrongPassword @RequestParam String password,
							  @RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		service.resetPassword(email, password);
	}

	// Validate exists with id, not ADMIN and no return
	@GetMapping("/validate-account-by-id")
	@ResponseStatus(HttpStatus.OK)
	public void validate(@RequestParam Long userId,
						 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		service.validate(userId);
	}

	// Validate exists with email, not ADMIN and no return
	@GetMapping("/validate-account-by-email")
	@ResponseStatus(HttpStatus.OK)
	public void validate(@RequestParam String email,
						 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		service.validate(email);
	}

	// Validate exists with id and no return
	@GetMapping("/validate-account-existence-by-id")
	@ResponseStatus(HttpStatus.OK)
	public void validateExistence(@RequestParam Long userId,
								  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		service.validateExistence(userId);
	}

	// Validate exists with email and no return
	@GetMapping("/validate-account-existence-by-email")
	@ResponseStatus(HttpStatus.OK)
	public void validateExistence(@RequestParam String email,
								  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		service.validateExistence(email);
	}

	// Validate exists with id, not ADMIN and has return
	@GetMapping("/validate-account-and-return-by-id")
	@ResponseStatus(HttpStatus.OK)
	public AppUserClient returnValidated(@RequestParam Long userId,
										 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.returnValidated(userId);
	}

	// Validate exists with email, not ADMIN and has return
	@GetMapping("/validate-account-and-return-by-email")
	@ResponseStatus(HttpStatus.OK)
	public AppUserClient returnValidated(@RequestParam String email,
										 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.returnValidated(email);
	}

	// Validate exists with id and has return
	@GetMapping("/validate-account-existence-and-return-by-id")
	@ResponseStatus(HttpStatus.OK)
	public AppUserClient returnValidatedExistence(@RequestParam Long userId,
												  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.returnValidatedExistence(userId);
	}

	// Validate exists with email and has return
	@GetMapping("/validate-account-existence-and-return-by-email")
	@ResponseStatus(HttpStatus.OK)
	public AppUserClient returnValidatedExistence(@RequestParam String email,
												  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.returnValidatedExistence(email);
	}

}