package com.rekahdo.goodshop.vendor_service.feign.clients;

import com.rekahdo.goodshop.vendor_service.feign.dtos.AddressClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "address-service",
        url = "${application.address-service.url}",
        path = "/dev-hide/1090/addresses")
public interface AddressServiceClient {

    @GetMapping(path = "/retrieve-business-address", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    AddressClient retrieveBusiness(@RequestParam Long userId,
                                   @RequestHeader(required = false) String apiKey);

    @GetMapping(path = "/retrieve-bank-address", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    AddressClient retrieveBank(@RequestParam Long userId,
                               @RequestHeader(required = false) String apiKey);

}
