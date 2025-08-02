package com.rekahdo.ecommerce.goodshop._dtos.records;

import com.rekahdo.ecommerce.goodshop.validations.annotations.StrongPassword;
import jakarta.validation.constraints.NotNull;

public record Password(
        @StrongPassword
        @NotNull(message = "Enter a new Password. Field name: 'newPassword'.")
        String newPassword,

        @StrongPassword
        @NotNull(message = "Re-enter same Password. Field name: 'reEnterPassword'.")
        String reEnterPassword,

        @NotNull(message = "Enter the email for password reset. Field name: 'email'.")
        String email
) {}
