package com.rekahdo.goodshop.user_service.feign.clients;

import com.rekahdo.goodshop.user_service.feign.dtos.JwtResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Map;

@FeignClient(
        name = "authentication-service",
        url = "${application.authentication-service.url}",
        path = "/dev-hide/1030/authentication")
public interface AuthenticationServiceClient {

    @PostMapping(path = "/token/generate")
    @ResponseStatus(HttpStatus.OK)
    JwtResponse generateToken(@RequestParam String username, @RequestParam List<String> roles,
                              @RequestParam Map<String, ?> claims, @RequestParam Integer expireInHours,
                              @RequestHeader String apiKey);

    @PostMapping(path = "/token/retrieve-info")
    @ResponseStatus(HttpStatus.OK)
    ModelMap tokenInfo(@RequestParam String token, @RequestHeader String apiKey);

}
