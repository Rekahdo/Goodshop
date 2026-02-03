package com.rekahdo.goodshop.user_service.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "otp-service",
        url = "${application.otp-service.url}",
        path = "/dev-hide/1040/otp")
public interface OtpServiceClient {

    @PostMapping(path = "/validate-account-verification-otp")
    @ResponseStatus(HttpStatus.OK)
    boolean validateAcctVerificationOtp(@RequestParam Long userId,
                                               @RequestParam String sentTo,
                                               @RequestHeader(required = false) String apiKey);

    @PostMapping(path = "/delete-by-email")
    @ResponseStatus(HttpStatus.OK)
    void deleteByEmail(@RequestParam String email,
                       @RequestHeader(required = false) String apiKey);

}
