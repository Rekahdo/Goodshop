package com.rekahdo.goodshop.vendor_service.dtos.entities;

import com.rekahdo.goodshop.vendor_service.enums.AccountType;
import com.rekahdo.goodshop.vendor_service.feign.dtos.AddressClient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankDto implements ApiDto {

	private Long id;
	private String accountName;
	private String bankName;
	private String accountNumber;
	private AccountType accountType;
	private AddressClient address;

}