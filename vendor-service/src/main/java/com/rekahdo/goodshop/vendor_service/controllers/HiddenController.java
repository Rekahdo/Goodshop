package com.rekahdo.goodshop.vendor_service.controllers;

import com.rekahdo.goodshop.vendor_service.enums.UnresolvedReason;
import com.rekahdo.goodshop.vendor_service.services.*;
import com.rekahdo.goodshop.vendor_service.utilities.ApiKey;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(path = "/dev-hide/1120/vendors")
@RequiredArgsConstructor
@Hidden
public class HiddenController {

	private final HiddenService service;
	private final UnresolvedService unresolvedService;
	private final BusinessService businessService;
	private final ContactService contactService;
	private final BankService bankService;

	@GetMapping(path = "/retrieve-uris", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, URI> retrieveURIs(@RequestParam Long userId,
										@RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.retrieveURIs(userId);
	}

	@GetMapping("/validate-existence")
	@ResponseStatus(HttpStatus.OK)
	public void validateExistence(@RequestParam Long userId,
								  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		service.vendorExistence(userId);
	}

	@PutMapping(path = "/add-unresolved")
	@ResponseStatus(HttpStatus.OK)
	public void addUnresolved(@RequestParam Long userId, @RequestParam UnresolvedReason reason,
							  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		unresolvedService.add(userId, reason);
	}

	@DeleteMapping(path = "/delete-unresolved")
	@ResponseStatus(HttpStatus.OK)
	public void deleteUnresolved(@RequestParam Long userId, @RequestParam UnresolvedReason reason,
								 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		unresolvedService.delete(userId, reason);
	}

	// PHONE SERVICE
	@PutMapping(path = "/set-business-phone-added")
	@ResponseStatus(HttpStatus.OK)
	public void businessPhoneAdded(@RequestParam Long userId,
								   @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		businessService.setPhoneAdded(userId, true);
	}

	@PutMapping(path = "/set-business-phone-not-added")
	@ResponseStatus(HttpStatus.OK)
	public void businessPhoneNotAdded(@RequestParam Long userId,
									  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		businessService.setPhoneAdded(userId, false);
	}

	@PutMapping(path = "/set-contact-phone-added")
	@ResponseStatus(HttpStatus.OK)
	public void contactPhoneAdded(@RequestParam Long userId,
								  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		contactService.setPhoneAdded(userId, true);
	}

	@PutMapping(path = "/set-contact-phone-not-added")
	@ResponseStatus(HttpStatus.OK)
	public void contactPhoneNotAdded(@RequestParam Long userId,
									 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		contactService.setPhoneAdded(userId, false);
	}

	@PutMapping(path = "/set-contact-emergency-phone-added")
	@ResponseStatus(HttpStatus.OK)
	public void contactEmergencyPhoneAdded(@RequestParam Long userId,
										   @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		contactService.setEmergencyPhoneAdded(userId, true);
	}

	@PutMapping(path = "/set-contact-emergency-phone-not-added")
	@ResponseStatus(HttpStatus.OK)
	public void contactEmergencyPhoneNotAdded(@RequestParam Long userId,
											  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		contactService.setEmergencyPhoneAdded(userId, false);
	}

	// ADDRESS SERVICE
	@PutMapping(path = "/set-business-address-added")
	@ResponseStatus(HttpStatus.OK)
	public void businessAddressAdded(@RequestParam Long userId,
									 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		businessService.setAddressAdded(userId, true);
	}

	@PutMapping(path = "/set-business-address-not-added")
	@ResponseStatus(HttpStatus.OK)
	public void businessAddressNotAdded(@RequestParam Long userId,
										@RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		businessService.setAddressAdded(userId, false);
	}

	@PutMapping(path = "/set-bank-address-added")
	@ResponseStatus(HttpStatus.OK)
	public void bankAddressAdded(@RequestParam Long userId,
								 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		bankService.setAddressAdded(userId, true);
	}

	@PutMapping(path = "/set-bank-address-not-added")
	@ResponseStatus(HttpStatus.OK)
	public void bankAddressNotAdded(@RequestParam Long userId,
									@RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		bankService.setAddressAdded(userId, false);
	}

	// FILE SERVICE
	@PutMapping(path = "/set-business-certificate-added")
	@ResponseStatus(HttpStatus.OK)
	public void businessCertificateAdded(@RequestParam Long userId,
										 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		businessService.setCertificateAdded(userId, true);
	}

	@PutMapping(path = "/set-business-certificate-not-added")
	@ResponseStatus(HttpStatus.OK)
	public void businessCertificateNotAdded(@RequestParam Long userId,
											@RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		businessService.setCertificateAdded(userId, false);
	}

	@PutMapping(path = "/set-contact-id-proof-added")
	@ResponseStatus(HttpStatus.OK)
	public void contactIdProofAdded(@RequestParam Long userId,
								  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		contactService.setIdProofAdded(userId, true);
	}

	@PutMapping(path = "/set-contact-id-proof-not-added")
	@ResponseStatus(HttpStatus.OK)
	public void contactIdProofNotAdded(@RequestParam Long userId,
									   @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		contactService.setIdProofAdded(userId, false);
	}

}