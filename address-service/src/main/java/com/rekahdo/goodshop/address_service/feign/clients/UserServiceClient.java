package com.rekahdo.goodshop.address_service.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "user-service",
        url = "${application.user-service.url}",
        path = "/dev-hide/1020/users")
public interface UserServiceClient {

    @GetMapping("/validate-account-by-id")
    @ResponseStatus(HttpStatus.OK)
    void validate(@RequestParam Long userId,
                  @RequestHeader(required = false) String apiKey);

}
