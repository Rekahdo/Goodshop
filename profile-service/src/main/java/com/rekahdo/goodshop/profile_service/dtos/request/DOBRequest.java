package com.rekahdo.goodshop.profile_service.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record DOBRequest(
        // ^, $: start, end
        // (): option set, it contains options
        // |: means OR and used for options
        // ?: means optional, 0 present or not
        // 0?[1-9]: if first number is 0 or not, then second number should be between 1-9. Eg: 01 - 09, 1 - 9
        // [12][0-9]: if first number is 1 or 2, then second number should be between 0-9. Eg: 10 - 19, 20 - 29
        // 3[0-1]: if first number is 3, then second number should be 0 or 1. Eg: 30 or 31

        @Pattern(regexp = "^(0?[1-9]|[12][0-9]|3[01])$", message = "'day' should be from 1 to 31")
        @NotNull(message = "'day' cannot be null nor empty")
        String day,

        @Pattern(regexp = "^(0?[1-9]|1[0-2])$", message = "'month' should be from 1 to 12")
        @NotNull(message = "'month' cannot be null nor empty")
        String month,

        @Pattern(regexp = "^(19[0-9][0-9]|[2-9][0-9][0-9][0-9])$", message = "'year' should be from 1900")
        @NotNull(message = "'year' cannot be null nor empty")
        String year

) implements ApiRequest {}
