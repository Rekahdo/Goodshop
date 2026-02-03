package com.rekahdo.goodshop.otp_service.feign.dtos;

import jakarta.validation.constraints.NotNull;

public record SendOtpToNumber(
        @NotNull(message = "'otp' can not be null")
        Integer otp,

        @NotNull(message = "'title' can not be null")
        String title,

        @NotNull(message = "'countryCode' can not be null")
        String countryCode,

        @NotNull(message = "'number' can not be null")
        String number,

        @NotNull(message = "'expireInMins' can not be null")
        Integer expireInMins
) {}
