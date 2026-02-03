package com.rekahdo.goodshop.user_service.dtos.request;

import jakarta.validation.constraints.NotNull;

public record Login(

        @NotNull(message = "'username' can not be null")
        String username,

        @NotNull(message = "'password' can not be null")
        String password

) implements ApiRequest {}
