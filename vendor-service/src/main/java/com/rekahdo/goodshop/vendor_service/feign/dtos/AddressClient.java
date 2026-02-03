package com.rekahdo.goodshop.vendor_service.feign.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressClient {

	private Long id;
	private String purpose;
	private String name;
	private String flatNo;
	private Integer houseNo;
	private String street;
	private String streetType;
	private String offStreet;
	private String busStop;
	private String lga;
	private String city;
	private String state;
	private String country;
	private Long zipcode;
	private String address;

}