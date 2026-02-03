package com.rekahdo.goodshop.messaging_service.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record SimpleEmail(
        @NotNull(message = "'toEmail' can not be null")
        @Email
        String toEmail,

        @NotNull(message = "'title' can not be null")
        String title,

        @NotNull(message = "'text' can not be null")
        String text
) {}
