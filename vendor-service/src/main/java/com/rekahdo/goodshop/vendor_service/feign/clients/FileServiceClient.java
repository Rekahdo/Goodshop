package com.rekahdo.goodshop.vendor_service.feign.clients;

import com.rekahdo.goodshop.vendor_service.feign.dtos.AddressClient;
import com.rekahdo.goodshop.vendor_service.feign.dtos.FileClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@FeignClient(
        name = "file-service",
        url = "${application.file-service.url}",
        path = "/dev-hide/1100/files")
public interface FileServiceClient {

    @GetMapping(path = "/retrieve-business-certificate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    FileClient retrieveBusinessCertificate(@RequestParam Long userId,
                                           @RequestHeader(required = false) String apiKey);

    @GetMapping(path = "/retrieve-business-registration-documents", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<FileClient> retrieveBusinessRegistration(@RequestParam Long userId,
                                                  @RequestHeader(required = false) String apiKey);

    @GetMapping(path = "/retrieve-contact-person-id-proof", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    FileClient retrieveContactPersonIdProof(@RequestParam Long userId,
                                            @RequestHeader(required = false) String apiKey);

}
