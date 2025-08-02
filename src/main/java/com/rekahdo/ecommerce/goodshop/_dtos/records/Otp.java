package com.rekahdo.ecommerce.goodshop._dtos.records;

import jakarta.validation.constraints.NotNull;

public record Otp(
        @NotNull(message = "Enter the OTP sent to the email address. Field name: 'otp'.")
        Integer otp,

        @NotNull(message = "Enter the email address for OTP verification. Field name: 'email'.")
        String email) {
}
