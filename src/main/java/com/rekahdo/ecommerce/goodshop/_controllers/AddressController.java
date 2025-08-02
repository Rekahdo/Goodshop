package com.rekahdo.ecommerce.goodshop._controllers;

import com.rekahdo.ecommerce.goodshop._dtos.entities.AddressDto;
import com.rekahdo.ecommerce.goodshop._services.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity
@RequestMapping(path = "/api/v1/users/{userId}/addresses")
public class AddressController {

	@Autowired
	private AddressService service;

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId)")
	@PostMapping("")
	public ResponseEntity<?> addAddress(@PathVariable Long userId, @Valid @RequestBody AddressDto dto){
		return service.addAddress(userId, dto);
	}

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId)")
	@PutMapping("/{addressId}")
	public ResponseEntity<?> editAddress(@PathVariable Long userId, @PathVariable Long addressId,
										 @Valid @RequestBody AddressDto dto){
		return service.editAddress(userId, addressId, dto);
	}

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId) OR hasRole('ADMIN') OR hasRole('MODERATOR')")
	@GetMapping("")
	public ResponseEntity<?> getAddresses(@PathVariable Long userId){
		return service.getAddresses(userId);
	}

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId) OR hasRole('ADMIN') OR hasRole('MODERATOR')")
	@GetMapping("/{addressId}")
	public ResponseEntity<?> getAddress(@PathVariable Long userId, @PathVariable Long addressId){
		return service.getAddress(userId, addressId);
	}

	@PreAuthorize("@appUserSecurity.isUserAuth(authentication, #userId)")
	@DeleteMapping("/{addressId}")
	public ResponseEntity<?> deleteAddress(@PathVariable Long userId, @PathVariable Long addressId){
		return service.deleteAddress(userId, addressId);
	}

}
