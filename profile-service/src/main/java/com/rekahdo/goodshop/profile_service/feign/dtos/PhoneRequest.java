package com.rekahdo.goodshop.profile_service.feign.dtos;

import jakarta.validation.constraints.NotNull;

public record PhoneRequest(

        @NotNull(message = "'countryCode' can not be null")
        String countryCode,

        @NotNull(message = "'number' can not be null")
        String number

) {}
