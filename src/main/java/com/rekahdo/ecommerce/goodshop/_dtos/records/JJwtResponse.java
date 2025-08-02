package com.rekahdo.ecommerce.goodshop._dtos.records;

import jakarta.validation.constraints.NotNull;

public record JJwtResponse(@NotNull String token, String secretKey) {}