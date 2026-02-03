package com.rekahdo.goodshop.profile_service.feign.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneClient {

    private Long id;
    private String countryCode;
    private String number;
    private String validNumber;
    private boolean verified;

}