package com.rekahdo.goodshop.vendor_service.dtos.request;

import com.rekahdo.goodshop.vendor_service.enums.Role;
import com.rekahdo.goodshop.vendor_service.enums.Title;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContactRequest(

        @NotNull(message = "'title' can not be null")
        Title title,

        @NotBlank(message = "'name' can not be null")
        String name,

        @NotNull(message = "'role' can not be null")
        Role role

) implements ApiRequest {}
