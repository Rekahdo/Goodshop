package com.rekahdo.goodshop.admin_service.dtos.request;

import com.rekahdo.goodshop.admin_service.validations.annotations.AssignableRole;
import jakarta.validation.constraints.NotNull;

public record AdminRequest(

        @NotNull(message = "'role' can not be null")
        @AssignableRole
        String role

) implements ApiRequest {}
