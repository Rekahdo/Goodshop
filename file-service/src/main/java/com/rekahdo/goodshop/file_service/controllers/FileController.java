package com.rekahdo.goodshop.file_service.controllers;

import com.rekahdo.goodshop.file_service.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/files")
@RequiredArgsConstructor
@Validated
public class FileController {

    private final FileService service;

    @GetMapping(path = "/retrieve-all", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("#userId == authentication.principal.userId Or hasRole('ADMIN') OR MODERATOR")
    public ResponseEntity<MappingJacksonValue> retrieveAll(@RequestParam Long userId) throws IOException {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.retrieveAll(userId));
    }

}
