package com.rekahdo.goodshop.address_service.dtos.entities;

import com.rekahdo.goodshop.address_service.enums.AddressPurpose;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@Relation(collectionRelation = "addresses", itemRelation = "address")
public class AddressDto implements ApiDto {

    private Long id;
	private AddressPurpose purpose;
	private String name;
	private String flatNo;
	private Integer houseNo;
	private String street;
	private String offStreet;
	private String busStop;
	private String lga;
	private String city;
	private String state;
	private String country;
	private Long zipcode;
	private String address;
	private Long userId;

}