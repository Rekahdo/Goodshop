package com.rekahdo.goodshop.otp_service.feign.clients;

import com.rekahdo.goodshop.otp_service.feign.dtos.SendOtpToEmail;
import com.rekahdo.goodshop.otp_service.feign.dtos.SendOtpToNumber;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "messaging-service",
        url = "${application.messaging-service.url}",
        path = "/dev-hide/1050/message/send")
public interface MessagingServiceClient {

    @PostMapping(path = "/otp-to-email", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void sendOtpToEmail(@Valid @RequestBody SendOtpToEmail request,
                        @RequestHeader String apiKey);

    @PostMapping(path = "/otp-to-number", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void sendOtpToNumber(@Valid @RequestBody SendOtpToNumber request,
                         @RequestHeader String apiKey);

}
