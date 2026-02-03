package com.rekahdo.goodshop.profile_service.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@FeignClient(
        name = "phone-service",
        url = "${application.phone-service.url}",
        path = "/dev-hide/1070/phones")
public interface PhoneServiceClient {

    @GetMapping(path = "/retrieve-user-numbers-uri", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Map<String, URI> retrieveURIs(@RequestParam Long userId,
                                  @RequestHeader(required = false) String apiKey);

}
