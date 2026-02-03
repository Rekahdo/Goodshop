package com.rekahdo.goodshop.profile_service.controllers;

import com.rekahdo.goodshop.profile_service.services.HiddenService;
import com.rekahdo.goodshop.profile_service.utilities.ApiKey;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(path = "/dev-hide/1070/profile")
@RequiredArgsConstructor
@Hidden
public class HiddenController {

    private final HiddenService service;

    @GetMapping(path = "/retrieve-uris", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Map<String, URI> retrieveURIs(@RequestParam Long userId,
                                         @RequestHeader(required = false) String apiKey){
        ApiKey.validate(apiKey);
        return service.retrieveURIs(userId);
    }

}
