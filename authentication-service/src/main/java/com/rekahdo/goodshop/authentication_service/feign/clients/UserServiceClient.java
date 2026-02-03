package com.rekahdo.goodshop.authentication_service.feign.clients;

import com.rekahdo.goodshop.authentication_service.feign.dtos.Login;
import com.rekahdo.goodshop.authentication_service.dtos.response.JwtResponse;
import com.rekahdo.goodshop.authentication_service.feign.dtos.AppUserClient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "user-service",
        url = "${application.user-service.url}",
        path = "/dev-hide/1020/users")
public interface UserServiceClient {

    @PostMapping(path = "/login")
    @ResponseStatus(HttpStatus.OK)
    JwtResponse login(@Valid @RequestBody Login request,
                      @RequestHeader String apiKey);

}
