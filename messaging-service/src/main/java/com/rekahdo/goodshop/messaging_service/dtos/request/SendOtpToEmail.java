package com.rekahdo.goodshop.messaging_service.dtos.request;

import jakarta.validation.constraints.NotNull;

public record SendOtpToEmail(
        @NotNull(message = "'otp' can not be null")
        Integer otp,

        @NotNull(message = "'title' can not be null")
        String title,

        @NotNull(message = "'email' can not be null")
        String email,

        @NotNull(message = "'expireInMins' can not be null")
        Integer expireInMins
) implements ApiRequest {}
