package com.rekahdo.goodshop.file_service.controllers;

import com.rekahdo.goodshop.file_service.enums.Purpose;
import com.rekahdo.goodshop.file_service.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.file_service.feign.clients.VendorServiceClient;
import com.rekahdo.goodshop.file_service.services.FileService;
import com.rekahdo.goodshop.file_service.utilities.ApiKey;
import com.rekahdo.goodshop.file_service.validations.annotations.DocListType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/files")
@RequiredArgsConstructor
@Validated
@EnableMethodSecurity
public class BusinessRegistrationDocumentsController {

    private final FileService service;
    private final VendorServiceClient vendorService;

    @PostMapping(path = "/upload/business-registration-documents", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<Void> upload(@RequestParam Long userId,
                                       @RequestParam("other-registration-documents") @DocListType List<MultipartFile> fileList) {
        vendorService.validateExistence(userId, ApiKey.VENDOR_SERVICE);
        service.upload(userId, fileList, Purpose.BUSINESS_REGISTRATION_DOCUMENTS);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/retrieve/business-registration-documents", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("#userId == authentication.principal.userId Or hasRole('ADMIN')")
    public ResponseEntity<MappingJacksonValue> retrieve(@RequestParam Long userId) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.retrieveList(userId, Purpose.BUSINESS_REGISTRATION_DOCUMENTS));
    }

    @DeleteMapping(path = "/delete/business-registration-documents")
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<Void> delete(@RequestParam Long userId) {
        service.deleteFile(userId, Purpose.BUSINESS_REGISTRATION_DOCUMENTS);
        return ResponseEntity.accepted().build();
    }

}
