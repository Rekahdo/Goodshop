package com.rekahdo.ecommerce.goodshop._controllers;

import com.rekahdo.ecommerce.goodshop._dtos.entities.AuthorityDto;
import com.rekahdo.ecommerce.goodshop._services.AuthorityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity
@RequestMapping(path = "/api/v1/users/{userId}/authority")
public class AuthorityController {

	@Autowired
	private AuthorityService service;

	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDITOR')")
	@PostMapping(path = "", consumes = "application/json")
	public ResponseEntity<?> assignAuthority(@PathVariable Long userId, @Valid @RequestBody AuthorityDto dto) {
		return service.assignAuthority(userId, dto);
	}

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId) OR hasRole('ADMIN') OR hasRole('MODERATOR')")
	@GetMapping("")
	public ResponseEntity<?> getAuthority(@PathVariable Long userId) {
		return service.getAuthority(userId);
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDITOR')")
	@DeleteMapping(path = "")
	public ResponseEntity<?> deleteAuthority(@PathVariable Long userId) {
		return service.deleteAuthority(userId);
	}

}