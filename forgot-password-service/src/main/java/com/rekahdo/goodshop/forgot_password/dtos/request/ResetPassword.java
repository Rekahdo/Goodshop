package com.rekahdo.goodshop.forgot_password.dtos.request;

import com.rekahdo.goodshop.forgot_password.validations.annotations.StrongPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record ResetPassword(
        @NotNull(message = "'email' can not be null")
        @Email
        String email,

        @StrongPassword
        @NotNull(message = "'password' can not be null")
        String password
) implements ApiRequest {}
