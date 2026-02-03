package com.rekahdo.goodshop.otp_service.feign.clients;

import com.rekahdo.goodshop.otp_service.feign.dtos.PhoneClient;
import com.rekahdo.goodshop.otp_service.feign.enums.PhonePurpose;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "phone-service",
        url = "${application.phone-service.url}",
        path = "/dev-hide/1070/phones")
public interface PhoneServiceClient {

    @GetMapping(path = "/retrieve")
    @ResponseStatus(HttpStatus.OK)
    PhoneClient retrieve(@RequestParam Long userId,
                         @RequestParam PhonePurpose purpose,
                         @RequestHeader(required = false) String apiKey);

}
