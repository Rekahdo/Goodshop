package com.rekahdo.goodshop.address_service.feign.clients;

import com.rekahdo.goodshop.address_service.feign.enums.UnresolvedReason;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@FeignClient(
        name = "vendor-service",
        url = "${application.vendor-service.url}",
        path = "/dev-hide/1120/vendors")
public interface VendorServiceClient {

    @GetMapping("/validate-existence")
    @ResponseStatus(HttpStatus.OK)
    void validateExistence(@RequestParam Long userId,
                           @RequestHeader(required = false) String apiKey);

    @GetMapping(path = "/retrieve-uris", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Map<String, URI> retrieveURIs(@RequestParam Long userId,
                                  @RequestHeader(required = false) String apiKey);

    @PutMapping(path = "/add-unresolved")
    @ResponseStatus(HttpStatus.OK)
    void addUnresolved(@RequestParam Long userId, @RequestParam UnresolvedReason reason,
                       @RequestHeader(required = false) String apiKey);

    @DeleteMapping(path = "/delete-unresolved")
    @ResponseStatus(HttpStatus.OK)
    void deleteUnresolved(@RequestParam Long userId, @RequestParam UnresolvedReason reason,
                          @RequestHeader(required = false) String apiKey);

    @PutMapping(path = "/set-business-address-added")
    @ResponseStatus(HttpStatus.OK)
    void businessAddressAdded(@RequestParam Long userId,
                                     @RequestHeader(required = false) String apiKey);

    @PutMapping(path = "/set-business-address-not-added")
    @ResponseStatus(HttpStatus.OK)
    void businessAddressNotAdded(@RequestParam Long userId,
                                        @RequestHeader(required = false) String apiKey);

    @PutMapping(path = "/set-bank-address-added")
    @ResponseStatus(HttpStatus.OK)
    void bankAddressAdded(@RequestParam Long userId,
                                 @RequestHeader(required = false) String apiKey);

    @PutMapping(path = "/set-bank-address-not-added")
    @ResponseStatus(HttpStatus.OK)
    void bankAddressNotAdded(@RequestParam Long userId,
                                    @RequestHeader(required = false) String apiKey);

}
