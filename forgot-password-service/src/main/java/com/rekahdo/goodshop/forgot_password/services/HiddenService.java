package com.rekahdo.goodshop.forgot_password.services;

import com.rekahdo.goodshop.forgot_password.controllers.ForgotPasswordController;
import com.rekahdo.goodshop.forgot_password.dtos.request.ResetPassword;
import com.rekahdo.goodshop.forgot_password.feign.clients.OtpServiceClient;
import com.rekahdo.goodshop.forgot_password.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.forgot_password.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class HiddenService {

    public Map<String, URI> retrieveURIs() {
        return Map.of(
                "forgot-password", linkTo(methodOn(ForgotPasswordController.class).resetPassword(null)).toUri()
        );
    }

}
