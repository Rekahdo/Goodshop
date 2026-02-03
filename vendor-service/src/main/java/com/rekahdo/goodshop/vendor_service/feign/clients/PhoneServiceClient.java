package com.rekahdo.goodshop.vendor_service.feign.clients;

import com.rekahdo.goodshop.vendor_service.feign.dtos.PhoneClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.URI;
import java.util.Map;

@FeignClient(
        name = "phone-service",
        url = "${application.phone-service.url}",
        path = "/dev-hide/1070/phones")
public interface PhoneServiceClient {

    @GetMapping(path = "/retrieve-business-phone", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    PhoneClient retrieveBusiness(@RequestParam Long userId,
                                 @RequestHeader(required = false) String apiKey);

    @GetMapping(path = "/retrieve-contact-person-phone", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    PhoneClient retrieveContactPerson(@RequestParam Long userId,
                                      @RequestHeader(required = false) String apiKey);

    @GetMapping(path = "/retrieve-contact-person-emergency-phone", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    PhoneClient retrieveContactPersonEmergency(@RequestParam Long userId,
                                               @RequestHeader(required = false) String apiKey);

}
