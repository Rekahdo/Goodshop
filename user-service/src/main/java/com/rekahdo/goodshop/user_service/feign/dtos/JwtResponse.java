package com.rekahdo.goodshop.user_service.feign.dtos;

public record JwtResponse(String token, String secretKey, Integer expireInHours) {}