package com.rekahdo.goodshop.phone_service.dtos.entities;

import com.rekahdo.goodshop.phone_service.enums.PhonePurpose;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

import java.net.URI;

@Getter
@Setter
@Relation(collectionRelation = "phones", itemRelation = "phone")
public class PhoneDto implements ApiDto {

	private Long id;
	private String countryCode;
	private String number;
	private String validNumber;
	private PhonePurpose purpose;
	private boolean verified;
	private Long userId;
	private URI user;

}