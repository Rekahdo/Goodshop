package com.rekahdo.goodshop.file_service.feign.clients;

import com.rekahdo.goodshop.file_service.feign.enums.UnresolvedReason;
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

    @PutMapping(path = "/add-unresolved")
    @ResponseStatus(HttpStatus.OK)
    void addUnresolved(@RequestParam Long userId, @RequestParam UnresolvedReason reason,
                       @RequestHeader(required = false) String apiKey);

    @DeleteMapping(path = "/delete-unresolved")
    @ResponseStatus(HttpStatus.OK)
    void deleteUnresolved(@RequestParam Long userId, @RequestParam UnresolvedReason reason,
                          @RequestHeader(required = false) String apiKey);

    @PutMapping(path = "/set-business-certificate-added")
    @ResponseStatus(HttpStatus.OK)
    void businessCertificateAdded(@RequestParam Long userId,
                                         @RequestHeader(required = false) String apiKey);

    @PutMapping(path = "/set-business-certificate-not-added")
    @ResponseStatus(HttpStatus.OK)
    void businessCertificateNotAdded(@RequestParam Long userId,
                                            @RequestHeader(required = false) String apiKey);

    @PutMapping(path = "/set-contact-id-proof-added")
    @ResponseStatus(HttpStatus.OK)
    void contactIdProofAdded(@RequestParam Long userId,
                                    @RequestHeader(required = false) String apiKey);

    @PutMapping(path = "/set-contact-id-proof-not-added")
    @ResponseStatus(HttpStatus.OK)
    void contactIdProofNotAdded(@RequestParam Long userId,
                                       @RequestHeader(required = false) String apiKey);

}
