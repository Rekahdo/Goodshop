package com.rekahdo.goodshop.vendor_service.controllers;

import com.rekahdo.goodshop.vendor_service.dtos.entities.UnresolvedDto;
import com.rekahdo.goodshop.vendor_service.dtos.entities.VendorDto;
import com.rekahdo.goodshop.vendor_service.dtos.paginations.VendorPageRequest;
import com.rekahdo.goodshop.vendor_service.dtos.request.BusinessRequest;
import com.rekahdo.goodshop.vendor_service.enums.ApprovalStatus;
import com.rekahdo.goodshop.vendor_service.enums.UnresolvedReason;
import com.rekahdo.goodshop.vendor_service.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.vendor_service.services.UnresolvedService;
import com.rekahdo.goodshop.vendor_service.services.VendorService;
import com.rekahdo.goodshop.vendor_service.utilities.ApiKey;
import com.rekahdo.goodshop.vendor_service.validations.annotations.AcceptableId;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/vendors")
@RequiredArgsConstructor
@EnableMethodSecurity
@Validated
public class VendorController {

	private final VendorService service;
	private final UnresolvedService unresolvedService;

	@PostMapping(path = "/apply", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("#userId == authentication.principal.userId AND hasRole('USER')")
	public ResponseEntity<Long> apply(@AcceptableId @RequestParam Long userId,
									  @RequestBody @Valid BusinessRequest request){
		service.apply(userId, request);
		return ResponseEntity.ok().build();
	}

	@PostMapping(path = "/re-apply")
	@PreAuthorize("#userId == authentication.principal.userId AND hasRole('USER')")
	public ResponseEntity<Long> reapply(@AcceptableId @RequestParam Long userId){
		service.reapply(userId);
		return ResponseEntity.ok().build();
	}

	@PutMapping(path = "/approve")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MODERATOR') OR hasRole('EDITOR')")
	public ResponseEntity<Void> approve(@RequestParam Long userId){
		service.approvalStatus(userId, ApprovalStatus.APPROVED);
		return ResponseEntity.ok().build();
	}

	@PutMapping(path = "/denial")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MODERATOR') OR hasRole('EDITOR')")
	public ResponseEntity<Void> denial(@RequestParam Long userId){
		service.approvalStatus(userId, ApprovalStatus.DENIED);
		return ResponseEntity.ok().build();
	}

	@PutMapping(path = "/query", consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MODERATOR') OR hasRole('EDITOR')")
	public ResponseEntity<Void> query(@RequestParam Long userId,
												@RequestBody @NotEmpty List<UnresolvedReason> list){
		service.approvalStatus(userId, ApprovalStatus.ON_GOING);
		service.query(userId, list);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/retrieve", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("#userId == authentication.principal.userId OR hasRole('ADMIN') OR hasRole('MODERATOR')")
	public ResponseEntity<EntityModel<VendorDto>> retrieve(@RequestParam Long userId) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.retrieve(userId));
	}

	@GetMapping(path = "/retrieve-all", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasRole('ADMIN') OR hasRole('MODERATOR')")
	public PagedModel<EntityModel<VendorDto>> retrieveAll(@ModelAttribute VendorPageRequest request){
		return service.retrieveAll(request);
	}

	@DeleteMapping(path = "/delete")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#userId == authentication.principal.userId")
	public ResponseEntity<Void> delete(@RequestParam Long userId) {
		service.delete(userId);
		return ResponseEntity.ok().build();
	}

	@GetMapping(path = "/retrieve-unresolved-parameters", produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("#userId == authentication.principal.userId OR hasRole('ADMIN')")
	public ResponseEntity<List<UnresolvedDto>> getUnresolved(@RequestParam Long userId) {
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(unresolvedService.get(userId));
	}

}