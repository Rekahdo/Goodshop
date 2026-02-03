package com.rekahdo.goodshop.vendor_service.dtos.request;

import com.rekahdo.goodshop.vendor_service.enums.AccountType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BankRequest(

        @NotBlank(message = "'accountName' must contain a value")
        String accountName,

        @NotBlank(message = "'bankName' must contain a value")
        String bankName,

        @NotBlank(message = "'accountNumber' can not be null")
        String accountNumber,

        @NotNull(message = "'accountType' can not be null")
        AccountType accountType

) implements ApiRequest {}
