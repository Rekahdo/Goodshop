package com.rekahdo.goodshop.authentication_service.controllers;

import com.rekahdo.goodshop.authentication_service.feign.dtos.Login;
import com.rekahdo.goodshop.authentication_service.dtos.response.JwtResponse;
import com.rekahdo.goodshop.authentication_service.services.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

	private final AuthenticationService service;

	@PostMapping(path = "/login-user")
	@ResponseStatus(HttpStatus.OK)
	public JwtResponse loginUser(@Valid @RequestBody Login request){
		return service.loginUser(request);
	}

	@GetMapping(path = "/free")
	public ResponseEntity<String> free(){
		return ResponseEntity.ok().body("It is ok");
	}

}