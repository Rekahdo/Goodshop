package com.rekahdo.goodshop.authentication_service.services;

import com.rekahdo.goodshop.authentication_service.dtos.response.JwtResponse;
import com.rekahdo.goodshop.authentication_service.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.authentication_service.feign.dtos.AppUserClient;
import com.rekahdo.goodshop.authentication_service.security.jjwt.JwtTokenService;
import com.rekahdo.goodshop.authentication_service.utilities.ApiKey;
import io.jsonwebtoken.lang.Maps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HiddenService {

    private final JwtTokenService jwtService;
    private final UserServiceClient userService;

    public JwtResponse generateToken(String username, List<String> roles, Map<String, ?> claims, Integer expireInHours) {
        return jwtService.generate(username, roles, claims, expireInHours);
    }

    public JwtResponse generateToken() {
        return jwtService.generate("INTERNAL_ACCOUNT", List.of("ROLE_INTERNAL"),
                Map.of("userId", 0, "email", "account@gmail.com"), 1);
    }

    public ModelMap tokenInfo(String token) {
        String subject = jwtService.getSubject(token);
        Map<String, ?> otherClaims = jwtService.getOtherClaims(token);

        return new ModelMap().addAllAttributes(Map.of(
                "subject", subject,
                "username", subject,
                "userId", otherClaims.get("userId"),
                "email", otherClaims.get("email"),
                "roles", jwtService.getRoles(token)
        ));
    }

}