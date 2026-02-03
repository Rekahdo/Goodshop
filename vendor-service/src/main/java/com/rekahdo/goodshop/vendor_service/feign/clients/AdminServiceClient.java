package com.rekahdo.goodshop.vendor_service.feign.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "admin-service",
        url = "${application.admin-service.url}",
        path = "/dev-hide/1130/admin")
public interface AdminServiceClient {

    @PutMapping(path = "/assign-vendor-role")
    void assignVendor(@RequestParam Long userId,
                      @RequestHeader(required = false) String apiKey);

    @DeleteMapping("/delete-role")
    void delete(@RequestParam Long userId,
                @RequestHeader(required = false) String apiKey);

}
