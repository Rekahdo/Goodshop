package com.rekahdo.ecommerce.goodshop._controllers;

import com.rekahdo.ecommerce.goodshop._dtos.ProfileDto;
import com.rekahdo.ecommerce.goodshop._services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity
@RequestMapping(path = "/api/v1/users/{userId}/profile")
public class CategoryController {

	@Autowired
	private ProfileService service;

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId) OR hasRole('ADMIN') OR hasRole('MODERATOR')")
	@GetMapping("")
	public ResponseEntity<?> getProfile(@PathVariable Long userId) {
		return service.getProfile(userId);
	}

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId) OR hasRole('ADMIN') OR hasRole('EDITOR')")
	@PutMapping(path = "", consumes = "application/json")
	public ResponseEntity<?> putProfile(@PathVariable Long userId, @Valid @RequestBody ProfileDto dto) {
		return service.putProfile(userId, dto);
	}

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId) OR hasRole('ADMIN') OR hasRole('EDITOR')")
	@PatchMapping(path = "", consumes = "application/json")
	public ResponseEntity<?> patchProfile(@PathVariable Long userId, @Valid @RequestBody ProfileDto dto) {
		return service.patchProfile(userId, dto);
	}

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId) OR hasRole('ADMIN')")
	@DeleteMapping(path = "")
	public ResponseEntity<?> deleteProfile(@PathVariable Long userId) {
		return service.deleteProfile(userId);
	}

}
