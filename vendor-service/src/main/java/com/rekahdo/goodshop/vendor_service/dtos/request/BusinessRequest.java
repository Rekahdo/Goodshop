package com.rekahdo.goodshop.vendor_service.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record BusinessRequest(

        @NotBlank(message = "'registrationName' can not be null")
        String registrationName,

        String tradingName,

        @NotBlank(message = "'email' can not be null")
        @Email
        String email

) implements ApiRequest {}
