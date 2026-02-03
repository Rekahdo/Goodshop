package com.rekahdo.goodshop.vendor_service.dtos.entities;

import com.rekahdo.goodshop.vendor_service.enums.ApprovalStatus;
import com.rekahdo.goodshop.vendor_service.feign.clients.AddressServiceClient;
import com.rekahdo.goodshop.vendor_service.feign.clients.FileServiceClient;
import com.rekahdo.goodshop.vendor_service.feign.clients.PhoneServiceClient;
import com.rekahdo.goodshop.vendor_service.feign.dtos.PhoneClient;
import com.rekahdo.goodshop.vendor_service.utilities.ApiKey;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Relation(collectionRelation = "vendors", itemRelation = "vendor")
public class VendorDto implements ApiDto {

	private Long id;
	private String uid;
	private ApprovalStatus approvalStatus;
	private boolean approved;
	private Long userId;
	private LocalDateTime registeredAt;
	private BusinessDto business;
	private ContactDto contactPerson;
	private BankDto bank;
	private List<UnresolvedDto> unresolved;

	public void update(AddressServiceClient addressService, PhoneServiceClient phoneService, FileServiceClient fileService) {
		if(business != null) {
			business.setPhone(phoneService.retrieveBusiness(userId, ApiKey.PHONE_SERVICE));			// Business Address
			business.setAddress(addressService.retrieveBusiness(userId, ApiKey.ADDRESS_SERVICE));
			business.setCertificate(fileService.retrieveBusinessCertificate(userId, ApiKey.FILE_SERVICE));
			business.setOtherRegistrationDocuments(fileService.retrieveBusinessRegistration(userId, ApiKey.FILE_SERVICE));
		}

		if(contactPerson != null){
			contactPerson.setPhone(phoneService.retrieveContactPerson(userId, ApiKey.PHONE_SERVICE));
			contactPerson.setEmergencyPhone(phoneService.retrieveContactPersonEmergency(userId, ApiKey.PHONE_SERVICE));
			contactPerson.setIdentification(fileService.retrieveContactPersonIdProof(userId, ApiKey.FILE_SERVICE));
		}

		if(bank != null){
			bank.setAddress(addressService.retrieveBank(userId, ApiKey.ADDRESS_SERVICE));
		}
	}

}