package com.rekahdo.goodshop.file_service.feign.clients;

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
        name = "user-service",
        url = "${application.user-service.url}",
        path = "/dev-hide/1020/users")
public interface UserServiceClient {

    @GetMapping("/validate-account-by-id")
    @ResponseStatus(HttpStatus.OK)
    void validate(@RequestParam Long userId,
                  @RequestHeader(required = false) String apiKey);

    @GetMapping(path = "/retrieve-uris", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Map<String, URI> retrieveURIs(@RequestParam Long userId,
                                  @RequestHeader(required = false) String apiKey);

}
