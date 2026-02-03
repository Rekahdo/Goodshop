
package com.rekahdo.goodshop.vendor_service.dtos.entities;

import com.rekahdo.goodshop.vendor_service.enums.Role;
import com.rekahdo.goodshop.vendor_service.enums.Title;
import com.rekahdo.goodshop.vendor_service.feign.dtos.FileClient;
import com.rekahdo.goodshop.vendor_service.feign.dtos.PhoneClient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactDto implements ApiDto {

	private Long id;
	private Title title;
	private String name;
	private Role role;
	private PhoneClient phone;
	private PhoneClient emergencyPhone;
	private FileClient identification;

}