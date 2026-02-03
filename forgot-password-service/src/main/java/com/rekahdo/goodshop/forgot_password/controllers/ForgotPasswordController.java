package com.rekahdo.goodshop.forgot_password.controllers;

import com.rekahdo.goodshop.forgot_password.dtos.request.ResetPassword;
import com.rekahdo.goodshop.forgot_password.services.ForgotPasswordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordController {

	private final ForgotPasswordService service;

	@PutMapping(path = "/reset-password", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Void> resetPassword(@Valid @RequestBody ResetPassword record) {
		service.resetPassword(record);
		return ResponseEntity.ok().build();
	}

}