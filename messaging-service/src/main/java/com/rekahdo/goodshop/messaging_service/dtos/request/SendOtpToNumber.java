package com.rekahdo.goodshop.messaging_service.dtos.request;

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
) implements ApiRequest {}
