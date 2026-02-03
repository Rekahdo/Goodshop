package com.rekahdo.goodshop.authentication_service.feign.dtos;

import com.rekahdo.goodshop.authentication_service.dtos.request.ApiRequest;
import jakarta.validation.constraints.NotNull;

public record Login(

        @NotNull(message = "'username' can not be null")
        String username,

        @NotNull(message = "'password' can not be null")
        String password

) implements ApiRequest {}
