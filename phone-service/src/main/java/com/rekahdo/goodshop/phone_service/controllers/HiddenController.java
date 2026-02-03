package com.rekahdo.goodshop.phone_service.controllers;

import com.rekahdo.goodshop.phone_service.dtos.clients.PhoneClient;
import com.rekahdo.goodshop.phone_service.enums.PhonePurpose;
import com.rekahdo.goodshop.phone_service.services.HiddenService;
import com.rekahdo.goodshop.phone_service.utilities.ApiKey;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/dev-hide/1070/phones")
@RequiredArgsConstructor
@Hidden
public class HiddenController {

	private final HiddenService service;

	@GetMapping(path = "/retrieve-uris", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Map<String, URI> retrieveURIs(@RequestParam Long userId,
										 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.retrieveURIs(userId);
	}

	@GetMapping(path = "/retrieve")
	@ResponseStatus(HttpStatus.OK)
	public PhoneClient retrieve(@RequestParam Long userId,
							 @RequestParam PhonePurpose purpose,
							 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.findAndThrow(userId, purpose);
	}

	@GetMapping(path = "/retrieve-business-phone", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public PhoneClient retrieveBusiness(@RequestParam Long userId,
										@RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.find(userId, PhonePurpose.BUSINESS);
	}

	@GetMapping(path = "/retrieve-contact-person-phone", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public PhoneClient retrieveContactPerson(@RequestParam Long userId,
											 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.find(userId, PhonePurpose.CONTACT_PERSON);
	}

	@GetMapping(path = "/retrieve-contact-person-emergency-phone", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public PhoneClient retrieveContactPersonEmergency(@RequestParam Long userId,
													  @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.find(userId, PhonePurpose.CONTACT_PERSON_EMERGENCY);
	}

	@GetMapping(path = "/retrieve-all")
	@ResponseStatus(HttpStatus.OK)
	public List<PhoneClient> retrieveAll(@RequestParam Long userId,
							 @RequestParam List<PhonePurpose> purposes,
							 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.retrieveAll(userId, purposes);
	}

	@DeleteMapping(path = "/delete")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestParam Long userId, @RequestParam PhonePurpose purpose,
					   @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		service.delete(userId, purpose);
	}

	@DeleteMapping(path = "/delete-all")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestParam Long userId, @RequestParam List<PhonePurpose> purposes,
					   @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		service.deleteAll(userId, purposes);
	}

	@GetMapping(path = "/retrieve-client-by-userId-and-phone-number")
	@ResponseStatus(HttpStatus.OK)
	public PhoneClient retrieveByUserIdAndNumber(@RequestParam Long userId,
												 @RequestParam(name = "country-code") String countryCode,
												 @RequestParam String number,
												 @RequestHeader(required = false) String apiKey){
		ApiKey.validate(apiKey);
		return service.retrieveByUserIdAndNumber(userId, countryCode, number);
	}

}