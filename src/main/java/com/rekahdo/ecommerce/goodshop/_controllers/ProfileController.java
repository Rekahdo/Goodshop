package com.rekahdo.ecommerce.goodshop._controllers;

import com.rekahdo.ecommerce.goodshop._dtos.entities.ProfileDto;
import com.rekahdo.ecommerce.goodshop._services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController("apiProfileController")
@EnableMethodSecurity
@RequestMapping(path = "/api/v1/users/{userId}/profile")
public class ProfileController {

	@Autowired
	private ProfileService service;

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId)")
	@PostMapping(path = "", consumes = "application/json")
	public ResponseEntity<?> setProfile(@PathVariable Long userId, @Valid @RequestBody ProfileDto dto) {
		return service.setProfile(userId, dto);
	}

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId) OR hasRole('ADMIN') OR hasRole('MODERATOR')")
	@GetMapping("")
	public ResponseEntity<?> getProfile(@PathVariable Long userId) {
		return service.getProfile(userId);
	}

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId)")
	@DeleteMapping(path = "")
	public ResponseEntity<?> deleteProfile(@PathVariable Long userId) {
		return service.deleteProfile(userId);
	}

}
