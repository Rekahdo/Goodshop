package com.rekahdo.goodshop.user_service.dtos.request;

import com.rekahdo.goodshop.user_service.validations.annotations.StrongPassword;
import com.rekahdo.goodshop.user_service.validations.annotations.Username;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AccountRequest(
        @NotNull(message = "username can not be null")
        @Username
        String username,

        @NotNull(message = "password can not be null")
        @StrongPassword
        String password,

        @NotNull(message = "'username' can not be null")
        @Email
        String email
) implements ApiRequest {}
