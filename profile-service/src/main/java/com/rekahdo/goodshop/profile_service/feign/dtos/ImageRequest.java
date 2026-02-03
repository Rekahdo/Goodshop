package com.rekahdo.goodshop.profile_service.feign.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ImageRequest(

        @NotBlank(message = "'name' can not be null nor empty")
        String name,

        @NotBlank(message = "'type' can not be null nor empty")
        String type,

        @NotNull(message = "'imageData' can not be null")
        byte[] imageData

) {}
