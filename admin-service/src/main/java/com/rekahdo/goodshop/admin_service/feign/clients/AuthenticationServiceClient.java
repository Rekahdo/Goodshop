package com.rekahdo.goodshop.admin_service.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "authentication-service",
        url = "${application.authentication-service.url}",
        path = "/dev-hide/1030/authentication")
public interface AuthenticationServiceClient {

    @PostMapping(path = "/token/retrieve-info")
    @ResponseStatus(HttpStatus.OK)
    ModelMap tokenInfo(@RequestParam String token, @RequestHeader String apiKey);

}
