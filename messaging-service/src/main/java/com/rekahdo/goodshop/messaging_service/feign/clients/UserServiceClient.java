package com.rekahdo.goodshop.messaging_service.feign.clients;

import com.rekahdo.goodshop.messaging_service.feign.dtos.AppUserClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "user-service",
        url = "${application.user-service.url}",
        path = "/dev-hide/1020/users")
public interface UserServiceClient {

    @GetMapping("/validate-account-and-return-by-email")
    @ResponseStatus(HttpStatus.OK)
    AppUserClient returnValidated(@RequestParam String email,
                                  @RequestHeader(required = false) String apiKey);

}
