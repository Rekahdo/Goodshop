package com.rekahdo.goodshop.phone_service.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "otp-service",
        url = "${application.otp-service.url}",
        path = "/dev-hide/1040/otp")
public interface OtpServiceClient {

    @PostMapping(path = "/validate-profile-number-verification-otp")
    @ResponseStatus(HttpStatus.OK)
    boolean validateProfileNumberVerificationOtp(@RequestParam Long userId, @RequestParam String sentTo,
                                                 @RequestHeader(required = false) String apiKey);

    @PostMapping(path = "/validate-business-number-verification-otp")
    @ResponseStatus(HttpStatus.OK)
    boolean validateBusinessNumberVerificationOtp(@RequestParam Long userId, @RequestParam String sentTo,
                                                  @RequestHeader(required = false) String apiKey);

    @PostMapping(path = "/validate-contact-person-number-verification-otp")
    @ResponseStatus(HttpStatus.OK)
    boolean validateContactPersonNumberVerificationOtp(@RequestParam Long userId, @RequestParam String sentTo,
                                                       @RequestHeader(required = false) String apiKey);

    @PostMapping(path = "/validate-contact-person-number-emergency-verification-otp")
    @ResponseStatus(HttpStatus.OK)
    boolean validateContactPersonEmergencyNumberVerificationOtp(@RequestParam Long userId, @RequestParam String sentTo,
                                                                @RequestHeader(required = false) String apiKey);

    @PostMapping(path = "/delete-by-id")
    @ResponseStatus(HttpStatus.OK)
    void deleteByUserId(@RequestParam Long userId,
                        @RequestHeader(required = false) String apiKey);

}
