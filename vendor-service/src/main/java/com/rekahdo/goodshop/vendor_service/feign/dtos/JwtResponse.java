package com.rekahdo.goodshop.vendor_service.feign.dtos;

public record JwtResponse(String token, String secretKey, Integer expireInHours) {}