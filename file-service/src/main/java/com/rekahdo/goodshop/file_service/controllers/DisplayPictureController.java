package com.rekahdo.goodshop.file_service.controllers;

import com.rekahdo.goodshop.file_service.enums.Purpose;
import com.rekahdo.goodshop.file_service.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.file_service.services.FileService;
import com.rekahdo.goodshop.file_service.utilities.ApiKey;
import com.rekahdo.goodshop.file_service.validations.annotations.ImageType;
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

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/files")
@RequiredArgsConstructor
@Validated
@EnableMethodSecurity
public class DisplayPictureController {

    private final FileService service;
    private final UserServiceClient userService;

    // DISPLAY PICTURE
    @PostMapping(path = "/set/dp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<Void> upload(@RequestParam Long userId,
                       @RequestParam("image") @ImageType MultipartFile file) {
        userService.validate(userId, ApiKey.USER_SERVICE);
        service.upload(userId, file, Purpose.DISPLAY_PICTURE);
        return ResponseEntity.accepted().build();
    }

    @GetMapping(path = "/retrieve/dp", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("#userId == authentication.principal.userId Or hasRole('ADMIN')")
    public ResponseEntity<MappingJacksonValue> retrieve(@RequestParam Long userId) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
                .body(service.retrieve(userId, Purpose.DISPLAY_PICTURE));
    }

    @DeleteMapping(path = "/delete/dp")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("#userId == authentication.principal.userId")
    public ResponseEntity<Void> delete(@RequestParam Long userId) {
        service.deleteFile(userId, Purpose.DISPLAY_PICTURE);
        return ResponseEntity.accepted().build();
    }

}
