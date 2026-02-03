package com.rekahdo.goodshop.messaging_service.controllers;

import com.rekahdo.goodshop.messaging_service.dtos.request.SendOtpToEmail;
import com.rekahdo.goodshop.messaging_service.dtos.request.SendOtpToNumber;
import com.rekahdo.goodshop.messaging_service.services.MessageService;
import com.rekahdo.goodshop.messaging_service.utilities.ApiKey;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/dev-hide/1050/message/send")
@RequiredArgsConstructor
@Hidden
public class HiddenController {

    private final MessageService service;

    @PostMapping(path = "/otp-to-email", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendOtpToEmail(@Valid @RequestBody SendOtpToEmail request,
                               @RequestHeader(required = false) String apiKey) {
        ApiKey.validate(apiKey);
        service.sendOtpToEmail(request);
    }

    @PostMapping(path = "/otp-to-number", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendOtpToNumber(@Valid @RequestBody SendOtpToNumber request,
                                @RequestHeader(required = false) String apiKey){
        ApiKey.validate(apiKey);
        service.sendOtpToNumber(request);
    }

}
