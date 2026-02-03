package com.rekahdo.goodshop.phone_service.dtos.clients;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneClient implements ApiClient {

    private Long id;
    private String countryCode;
    private String number;
    private String validNumber;
    private String purpose;
    private boolean verified;
    private Long userId;

}