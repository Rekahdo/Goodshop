package com.rekahdo.goodshop.otp_service.feign.clients;

import com.rekahdo.goodshop.otp_service.feign.dtos.AppUserClient;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@FeignClient(
        name = "user-service",
        url = "${application.user-service.url}",
        path = "/dev-hide/1020/users")
public interface UserServiceClient {

    @GetMapping("/validate-account-and-return-by-id")
    @ResponseStatus(HttpStatus.OK)
    AppUserClient returnValidated(@RequestParam Long userId,
                                  @RequestHeader(required = false) String apiKey);

    @GetMapping("/validate-account-and-return-by-email")
    @ResponseStatus(HttpStatus.OK)
    AppUserClient returnValidated(@RequestParam String email,
                                  @RequestHeader(required = false) String apiKey);

    @GetMapping(path = "/retrieve-uris", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Map<String, URI> retrieveURIs(@RequestParam Long userId,
                                  @RequestHeader(required = false) String apiKey);

}
