package com.rekahdo.goodshop.vendor_service.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Getter
public enum UnresolvedReason {

	BUSINESS_ADDRESS_NOT_PROVIDED("bs1", "Business address has not been provided"),
	BUSINESS_EMAIL_NOT_VERIFIED("bs2", "Business email has not been verified"),
	BUSINESS_PHONE_NOT_PROVIDED("bs3", "Business phone number has not been provided"),
	BUSINESS_PHONE_NOT_VERIFIED("bs4", "Business phone number has not been verified"),
	BUSINESS_CERTIFICATE_NOT_PROVIDED("bs5", "Business certificate has not been provided"),
	BUSINESS_CERTIFICATE_UNACCEPTABLE("bs6", "Business certificate is not acceptable"),
	CONTACT_PERSON_INFO_NOT_PROVIDED("c1", "Contact Person information has not been provided"),
	CONTACT_PERSON_PHONE_NOT_PROVIDED("c2", "Contact Person phone number has not been provided"),
	CONTACT_PERSON_PHONE_NOT_VERIFIED("c3", "Contact Person phone number has not been verified"),
	CONTACT_PERSON_EMERGENCY_PHONE_NOT_PROVIDED("c4", "Contact Person emergency phone number has not been provided"),
	CONTACT_PERSON_EMERGENCY_PHONE_NOT_VERIFIED("c5", "Contact Person emergency phone number has not been verified"),
	CONTACT_PERSON_ID_PROOF_NOT_PROVIDED("c6", "Contact Person ID-proof has not been provided"),
	CONTACT_PERSON_ID_PROOF_INVALID("c7", "Contact Person ID-proof is invalid"),
	BANK_INFO_NOT_PROVIDED("bk1", "Bank details for money transfer has not been provided"),
	BANK_ADDRESS_NOT_PROVIDED("bk2", "Bank address has not been provided");

	public final String index;
	public final String message;

	UnresolvedReason(String index, String message) {
		this.index = index;
		this.message = message;
	}

	public static UnresolvedReason findByIndex(String index){
		Optional<UnresolvedReason> optional = Arrays.stream(UnresolvedReason.values())
				.filter(role -> Objects.equals(role.getIndex(), index))
				.findFirst();
		return optional.orElse(null);
	}
    
}