package com.rekahdo.goodshop.forgot_password.feign.clients;

import com.rekahdo.goodshop.forgot_password.feign.dtos.AppUserClient;
import com.rekahdo.goodshop.forgot_password.validations.annotations.StrongPassword;
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

    @GetMapping("/validate-account-and-return-by-email")
    @ResponseStatus(HttpStatus.OK)
    AppUserClient returnValidated(@RequestParam String email,
                                  @RequestHeader(required = false) String apiKey);

    @PutMapping(path = "/reset-password")
    @ResponseStatus(HttpStatus.OK)
    void resetPassword(@Valid @RequestParam @Email String email,
                       @Valid @StrongPassword @RequestParam String password,
                       @RequestHeader String apiKey);

}
