package com.rekahdo.goodshop.file_service.controllers;

import com.rekahdo.goodshop.file_service.dtos.entities.ImageDto;
import com.rekahdo.goodshop.file_service.services.FileService;
import com.rekahdo.goodshop.file_service.services.PublicService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/api/v1/files/public")
@RequiredArgsConstructor
public class PublicController {

    private final PublicService service;

    @PostMapping(path = "/resize-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public byte[] resizeImage(
            @RequestParam(name = "width") int w,
            @RequestParam(name = "height") int h,
            @RequestParam(required = false, name = "keep-aspect-ratio", defaultValue = "true") boolean keepAspectRatio,
            @RequestParam(required = false, name = "download-to-path") String downloadTo,
            @RequestParam MultipartFile image) throws IOException {
        return service.resizeImage(image, w, h, keepAspectRatio, downloadTo);
    }

    @Operation(hidden = true)
    @PostMapping(path = "/compress-image-to-kb",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public byte[] compressImageToKb(
            @RequestParam(required = false, defaultValue = "50") Integer kb,
            @RequestParam(required = false, name = "download-to-path") String downloadTo,
            @RequestParam MultipartFile image) throws IOException {
        return service.compressImageToKb(image, kb, downloadTo);
    }

    @PostMapping(path = "/scale-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public byte[] scaleImage(
            @RequestParam(required = false, defaultValue = "1.0") double factor,
            @RequestParam(required = false, name = "download-to-path") String downloadTo,
            @RequestParam MultipartFile image) throws IOException {
        return service.scaleImage(image, factor, downloadTo);
    }

    @PostMapping(path = "/crop-image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public byte[] cropImage(
            @RequestParam(required = false) Positions position,
            @RequestParam(required = false, defaultValue = "0", name = "x-axis") int x,
            @RequestParam(required = false, defaultValue = "0", name = "y-axis") int y,
            @RequestParam(name = "width") int w,
            @RequestParam(name = "height") int h,
            @RequestParam(required = false, defaultValue = "false", name = "make-square") boolean makeSquare,
            @RequestParam(required = false, name = "download-to-path") String downloadTo,
            @RequestParam MultipartFile image) throws IOException {
        return service.cropImage(image, position, x, y, w, h, makeSquare, downloadTo);
    }

    @GetMapping(path = "/image-info",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ImageDto imageInfo(@RequestParam MultipartFile image) throws IOException {
        return service.imageInfo(image);
    }

}
