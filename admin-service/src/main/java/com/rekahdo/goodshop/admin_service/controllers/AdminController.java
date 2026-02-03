package com.rekahdo.goodshop.admin_service.controllers;

import com.rekahdo.goodshop.admin_service.enums.Role;
import com.rekahdo.goodshop.admin_service.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.admin_service.services.AdminService;
import com.rekahdo.goodshop.admin_service.utilities.ApiKey;
import com.rekahdo.goodshop.admin_service.validations.annotations.AcceptableId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/admin")
@RequiredArgsConstructor
@EnableMethodSecurity
@Validated
public class AdminController {

	private final AdminService service;
	private final UserServiceClient userService;

	@PutMapping(path = "/assign-moderator-role")
	public void assignModerator(@RequestParam @AcceptableId Long userId) {
		userService.validate(userId, ApiKey.USER_SERVICE);
		service.assign(userId, Role.MODERATOR);
	}

	@PutMapping(path = "/assign-editor-role")
	public void assignEditor(@RequestParam @AcceptableId Long userId) {
		userService.validate(userId, ApiKey.USER_SERVICE);
		service.assign(userId, Role.EDITOR);
	}

	@PutMapping(path = "/assign-inspector-role")
	public void assignInspector(@RequestParam @AcceptableId Long userId) {
		userService.validate(userId, ApiKey.USER_SERVICE);
		service.assign(userId, Role.INSPECTOR);
	}

	@DeleteMapping("/delete-role")
	public void delete(@RequestParam @AcceptableId Long userId) {
		service.delete(userId);
	}

}