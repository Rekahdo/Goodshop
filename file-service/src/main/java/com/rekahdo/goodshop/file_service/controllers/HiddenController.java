package com.rekahdo.goodshop.file_service.controllers;

import com.rekahdo.goodshop.file_service.dtos.clients.FileClient;
import com.rekahdo.goodshop.file_service.enums.Purpose;
import com.rekahdo.goodshop.file_service.services.FileService;
import com.rekahdo.goodshop.file_service.services.HiddenService;
import com.rekahdo.goodshop.file_service.utilities.ApiKey;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/dev-hide/1100/files")
@RequiredArgsConstructor
@Hidden
public class HiddenController {

    private final HiddenService service;

    @GetMapping(path = "/retrieve-uris", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, URI> retrieveURIs(@RequestParam Long userId,
                                         @RequestHeader(required = false) String apiKey) throws IOException {
        ApiKey.validate(apiKey);
        return service.retrieveURIs(userId);
    }

    @GetMapping(path = "/retrieve-business-certificate", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public FileClient retrieveBusinessCertificate(@RequestParam Long userId,
                                       @RequestHeader(required = false) String apiKey){
        ApiKey.validate(apiKey);
        return service.find(userId, Purpose.BUSINESS_CERTIFICATE);
    }

    @GetMapping(path = "/retrieve-business-registration-documents", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<FileClient> retrieveBusinessRegistration(@RequestParam Long userId,
                                                         @RequestHeader(required = false) String apiKey){
        ApiKey.validate(apiKey);
        return service.findAll(userId, Purpose.BUSINESS_REGISTRATION_DOCUMENTS);
    }

    @GetMapping(path = "/retrieve-contact-person-id-proof", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public FileClient retrieveContactPersonIdProof(@RequestParam Long userId,
                                                   @RequestHeader(required = false) String apiKey){
        ApiKey.validate(apiKey);
        return service.find(userId, Purpose.CONTACT_PERSON_ID_PROOF);
    }

}
