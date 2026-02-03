package com.rekahdo.goodshop.user_service.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(
        name = "admin-service",
        url = "${application.admin-service.url}",
        path = "/dev-hide/1130/admin")
public interface AdminServiceClient {

    @GetMapping(path = "/retrieve-account-roles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<String> retrieveRoles(@RequestParam Long userId, @RequestHeader String apiKey);

}
