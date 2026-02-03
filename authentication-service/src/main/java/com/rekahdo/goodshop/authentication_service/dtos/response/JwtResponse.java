package com.rekahdo.goodshop.authentication_service.dtos.response;

public record JwtResponse(String token, String secretKey, Integer expireInHours) implements ApiResponse {}