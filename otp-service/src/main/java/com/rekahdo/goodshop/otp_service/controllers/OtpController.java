package com.rekahdo.goodshop.otp_service.controllers;

import com.rekahdo.goodshop.otp_service.enums.OtpPurpose;
import com.rekahdo.goodshop.otp_service.feign.enums.PhonePurpose;
import com.rekahdo.goodshop.otp_service.services.OtpService;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/otp")
@RequiredArgsConstructor
@Validated
@EnableMethodSecurity
public class OtpController {

	private final OtpService service;

	@PostMapping(path = "/request-account-verification-otp")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#email == authentication.principal.email")
	public void requestForAcctVerification(@RequestParam @Email String email){
		service.requestEmailVerification(email, OtpPurpose.ACCOUNT_VERIFICATION);
	}

	@PostMapping(path = "/request-profile-number-verification-otp")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#userId == authentication.principal.userId")
	public void requestProfileNumVerification(@RequestParam Long userId){
		service.requestPhoneNumberVerification(userId, OtpPurpose.PROFILE_NUMBER_VERIFICATION, PhonePurpose.PROFILE);
	}

	@PostMapping(path = "/request-business-email-verification-otp")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#userId == authentication.principal.userId")
	public void requestBusinessEmailVerification(@RequestParam Long userId){
		service.requestEmailVerification(userId, OtpPurpose.BUSINESS_EMAIL_VERIFICATION);
	}

	@PostMapping(path = "/request-business-number-verification-otp")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#userId == authentication.principal.userId")
	public void requestBusinessNumVerification(@RequestParam Long userId){
		service.requestPhoneNumberVerification(userId, OtpPurpose.BUSINESS_NUMBER_VERIFICATION, PhonePurpose.BUSINESS);
	}

	@PostMapping(path = "/request-contact-person-number-verification-otp")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("#userId == authentication.principal.userId")
	public void requestContactPersonNumVerification(@RequestParam Long userId){
		service.requestPhoneNumberVerification(userId, OtpPurpose.CONTACT_PERSON_NUMBER_VERIFICATION, PhonePurpose.CONTACT_PERSON);
	}

	@PostMapping(path = "/request-contact-person-emergency-number-verification-otp")
	@ResponseStatus(HttpStatus.OK)
	public void requestContactPersonEmergencyNumVerification(@RequestParam Long userId){
		service.requestPhoneNumberVerification(userId, OtpPurpose.CONTACT_PERSON_EMERGENCY_NUMBER_VERIFICATION, PhonePurpose.CONTACT_PERSON_EMERGENCY);
	}

	@PostMapping(path = "/request-forgot-password-otp")
	@ResponseStatus(HttpStatus.OK)
	public void requestForPasswordReset(@RequestParam @Email String email){
		service.requestEmailVerification(email, OtpPurpose.FORGOT_PASSWORD);
	}

	@PostMapping(path = "/verify")
	@ResponseStatus(HttpStatus.OK)
	public void verifyOtp(@RequestParam Integer otp) {
		service.verifyOtp(otp);
	}

}