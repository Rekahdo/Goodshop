package com.rekahdo.ecommerce.goodshop._dtos.records;

import jakarta.validation.constraints.Email;

public record ApiEmail(
        @Email(message = "Specify the email. Field name: 'email'.")
        String email
) {}
