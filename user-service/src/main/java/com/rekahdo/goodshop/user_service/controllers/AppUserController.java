package com.rekahdo.goodshop.user_service.controllers;

import com.rekahdo.goodshop.user_service.dtos.entities.AppUserDto;
import com.rekahdo.goodshop.user_service.dtos.paginations.AccountPageRequest;
import com.rekahdo.goodshop.user_service.dtos.request.AccountRequest;
import com.rekahdo.goodshop.user_service.services.AppUserService;
import com.rekahdo.goodshop.user_service.validations.annotations.ModifiableId;
import com.rekahdo.goodshop.user_service.validations.annotations.StrongPassword;
import com.rekahdo.goodshop.user_service.validations.annotations.Username;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/users")
@RequiredArgsConstructor
@EnableMethodSecurity
@Validated
public class AppUserController {

	private final AppUserService service;

	@PostMapping(path = "/create-account", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Long add(@Valid @RequestBody AccountRequest request){
		return service.add(request);
	}

	@PutMapping(path = "/verify-account")
	@PreAuthorize("#email == authentication.principal.email")
	public ResponseEntity<Void> verify(@RequestParam @Email @Valid String email){
		service.verify(email);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/retrieve-account")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#id == authentication.principal.userId OR hasRole('ADMIN') OR hasRole('MODERATOR')")
	public ResponseEntity<EntityModel<AppUserDto>> retrieveById(@RequestParam Long id){
		return service.retrieveById(id);
	}

	@GetMapping(path = "/retrieve-all-accounts", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MODERATOR')")
	public PagedModel<EntityModel<AppUserDto>> retrieveAll(@ModelAttribute AccountPageRequest request){
		return service.retrieveAll(request);
	}

	@DeleteMapping("/delete-account")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#id == authentication.principal.userId OR hasRole('ADMIN')")
	public ResponseEntity<Void> delete(@ModifiableId @RequestParam Long id){
		service.delete(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping(path = "/edit-username")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#id == authentication.principal.userId")
	public ResponseEntity<Void> editUsername(@RequestParam Long id,
							 @Username @RequestParam String newUsername,
							 @RequestParam String accountPassword) {
		service.editUsername(id, newUsername, accountPassword);
		return ResponseEntity.ok().build();
	}

	@PutMapping(path = "/edit-email")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#id == authentication.principal.userId")
	public ResponseEntity<Void> editEmail(@RequestParam Long id,
						  @Valid @Email @RequestParam String newEmail,
						  @RequestParam String accountPassword) {
		service.editEmail(id, newEmail, accountPassword);
		return ResponseEntity.ok().build();
	}

	@PutMapping(path = "/edit-password")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#id == authentication.principal.userId")
	public ResponseEntity<Void> editPassword(@RequestParam Long id,
							 @Valid @StrongPassword @RequestParam String newPassword,
							 @RequestParam String accountPassword) {
		service.editPassword(id, newPassword, accountPassword);
		return ResponseEntity.ok().build();
	}

}