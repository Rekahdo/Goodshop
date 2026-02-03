package com.rekahdo.goodshop.address_service.dtos.clients;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;

@Getter
@Setter
public class AddressClient implements ApiClient{

	private Long id;
	private String purpose;
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
	private Long userId;
	private URI self;
	private String address;

}