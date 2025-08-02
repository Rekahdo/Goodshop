package com.rekahdo.ecommerce.goodshop._controllers;

import com.rekahdo.ecommerce.goodshop._dtos.records.ApiEmail;
import com.rekahdo.ecommerce.goodshop._dtos.records.Otp;
import com.rekahdo.ecommerce.goodshop._dtos.records.Password;
import com.rekahdo.ecommerce.goodshop._services.ForgotPasswordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableMethodSecurity
@RequestMapping(path = "/api/v1/forgot/password")
public class ForgotPasswordController {

	@Autowired
	private ForgotPasswordService service;

	@PostMapping(path = "/verify/email", consumes = "application/json")
	public ResponseEntity<?> verifyEmail(@Valid @RequestBody ApiEmail email) {
		return service.verifyEmail(email);
	}

	@PostMapping(path = "/verify/otp", consumes = "application/json")
	public ResponseEntity<?> verifyOTP(@Valid @RequestBody Otp otp) {
		return service.verifyOTP(otp);
	}

	@PostMapping(path = "/reset", consumes = "application/json")
	public ResponseEntity<?> resetPassword(@Valid @RequestBody Password password) {
		return service.resetPassword(password);
	}

}