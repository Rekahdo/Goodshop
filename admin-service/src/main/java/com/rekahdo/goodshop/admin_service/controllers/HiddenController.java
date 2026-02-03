package com.rekahdo.goodshop.admin_service.controllers;

import com.rekahdo.goodshop.admin_service.enums.Role;
import com.rekahdo.goodshop.admin_service.services.AdminService;
import com.rekahdo.goodshop.admin_service.services.HiddenService;
import com.rekahdo.goodshop.admin_service.utilities.ApiKey;
import com.rekahdo.goodshop.admin_service.validations.annotations.AcceptableId;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/dev-hide/1130/admin")
@RequiredArgsConstructor
@Hidden
public class HiddenController {

	private final HiddenService service;
	private final AdminService adminService;

	@PutMapping(path = "/assign-vendor-role")
	public void assignVendor(@RequestParam Long userId,
							 @RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		adminService.assign(userId, Role.VENDOR);
	}

	@DeleteMapping("/delete-role")
	public void delete(@RequestParam Long userId,
					   @RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		adminService.delete(userId);
	}

	@GetMapping(path = "/retrieve-uris", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, URI> retrieveURIs(@RequestParam Long userId,
										 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.retrieveURIs(userId);
	}

	@GetMapping(path = "/retrieve-account-roles", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<String> retrieveRoles(@RequestParam Long userId,
									  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.retrieveRoles(userId);
	}

}