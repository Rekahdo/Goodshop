package com.rekahdo.goodshop.profile_service.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ProfileRequest(

        // [a-zA-Z]: Contains a to z or A to Z
        // +: contains 1 or more of [a-zA-Z]
        // *: contains 0 or more of [a-zA-Z]

        @NotBlank(message = "'firstName' can not be null nor empty")
        @Pattern(regexp = "[a-zA-Z]+", message = "'firstName' must contains alphabets only")
        @Size(min = 2, max = 20, message = "'firstName' must be between 2 - 20 characters long")
        String firstName,

        @Pattern(regexp = "[a-zA-Z]*", message = "'middleName' must contains alphabets only")
        @Size(min = 2, max = 20, message = "'middleName' must be between 2 - 20 characters long")
        String middleName,

        @NotBlank(message = "'lastName' can not be null nor empty")
        @Pattern(regexp = "[a-zA-Z]+", message = "'lastName' must contains alphabets only")
        @Size(min = 2, max = 20, message = "'lastName' must be between 2 - 20 characters long")
        String lastName

) implements ApiRequest {}
