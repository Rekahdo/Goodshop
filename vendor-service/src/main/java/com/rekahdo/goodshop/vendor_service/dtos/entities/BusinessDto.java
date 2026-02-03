
package com.rekahdo.goodshop.vendor_service.dtos.entities;

import com.rekahdo.goodshop.vendor_service.feign.dtos.AddressClient;
import com.rekahdo.goodshop.vendor_service.feign.dtos.FileClient;
import com.rekahdo.goodshop.vendor_service.feign.dtos.PhoneClient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BusinessDto implements ApiDto {

	private Long id;
	private String registrationName;
	private String tradingName;
	private String email;
	private boolean emailVerified;
	private PhoneClient phone;
	private AddressClient address;
	private FileClient certificate;
	private List<FileClient> otherRegistrationDocuments;

}