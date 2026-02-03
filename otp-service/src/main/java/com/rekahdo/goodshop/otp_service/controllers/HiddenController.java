package com.rekahdo.goodshop.otp_service.controllers;

import com.rekahdo.goodshop.otp_service.enums.OtpPurpose;
import com.rekahdo.goodshop.otp_service.services.HiddenService;
import com.rekahdo.goodshop.otp_service.utilities.ApiKey;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/dev-hide/1040/otp")
@RequiredArgsConstructor
@Hidden
public class HiddenController {

	private final HiddenService service;

	@PostMapping(path = "/validate-account-verification-otp")
	@ResponseStatus(HttpStatus.OK)
	public boolean validateAcctVerificationOtp(@RequestParam Long userId, @RequestParam String sentTo,
											   @RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		return service.validate(sentTo, userId, OtpPurpose.ACCOUNT_VERIFICATION);
	}

	@PostMapping(path = "/validate-business-email-verification-otp")
	@ResponseStatus(HttpStatus.OK)
	public boolean validateBusinessEmailVerificationOtp(@RequestParam Long userId, @RequestParam String sentTo,
														@RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		return service.validate(sentTo, userId, OtpPurpose.BUSINESS_EMAIL_VERIFICATION);
	}

	@PostMapping(path = "/validate-business-number-verification-otp")
	@ResponseStatus(HttpStatus.OK)
	public boolean validateBusinessNumberVerificationOtp(@RequestParam Long userId, @RequestParam String sentTo,
														 @RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		return service.validate(sentTo, userId, OtpPurpose.BUSINESS_NUMBER_VERIFICATION);
	}

	@PostMapping(path = "/validate-profile-number-verification-otp")
	@ResponseStatus(HttpStatus.OK)
	public boolean validateProfileNumberVerificationOtp(@RequestParam Long userId, @RequestParam String sentTo,
														@RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		return service.validate(sentTo, userId, OtpPurpose.PROFILE_NUMBER_VERIFICATION);
	}

	@PostMapping(path = "/validate-contact-person-number-verification-otp")
	@ResponseStatus(HttpStatus.OK)
	public boolean validateContactPersonNumberVerificationOtp(@RequestParam Long userId, @RequestParam String sentTo,
															  @RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		return service.validate(sentTo, userId, OtpPurpose.CONTACT_PERSON_NUMBER_VERIFICATION);
	}

	@PostMapping(path = "/validate-contact-person-number-emergency-verification-otp")
	@ResponseStatus(HttpStatus.OK)
	public boolean validateContactPersonEmergencyNumberVerificationOtp(@RequestParam Long userId, @RequestParam String sentTo,
																	   @RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		return service.validate(sentTo, userId, OtpPurpose.CONTACT_PERSON_EMERGENCY_NUMBER_VERIFICATION);
	}

	@PostMapping(path = "/validate-forgot-password-otp")
	@ResponseStatus(HttpStatus.OK)
	public boolean validateForgotPasswordOtp(@RequestParam Long userId, @RequestParam String sentTo,
											 @RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		return service.validate(sentTo, userId, OtpPurpose.FORGOT_PASSWORD);
	}

	@PostMapping(path = "/delete-by-id")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByUserId(@RequestParam Long userId,
							   @RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		service.deleteByUserId(userId);
	}

	@PostMapping(path = "/delete-by-otp")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByOtp(@RequestParam Integer otp,
							@RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		service.deleteByOtp(otp);
	}

	@PostMapping(path = "/delete-by-email")
	@ResponseStatus(HttpStatus.OK)
	public void deleteByEmail(@RequestParam String email,
							  @RequestHeader(required = false) String apiKey) {
		ApiKey.validate(apiKey);
		service.deleteByEmail(email);
	}

}