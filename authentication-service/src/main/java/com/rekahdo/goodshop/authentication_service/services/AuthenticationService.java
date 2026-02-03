package com.rekahdo.goodshop.authentication_service.services;

import com.rekahdo.goodshop.authentication_service.feign.dtos.Login;
import com.rekahdo.goodshop.authentication_service.dtos.response.JwtResponse;
import com.rekahdo.goodshop.authentication_service.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.authentication_service.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserServiceClient userService;

    @Value("${application.account.username}")
    private String username;

    @Value("${application.account.role}")
    private String role;

    public JwtResponse loginUser(Login request) {
        return userService.login(request, ApiKey.USER_SERVICE);
    }

}