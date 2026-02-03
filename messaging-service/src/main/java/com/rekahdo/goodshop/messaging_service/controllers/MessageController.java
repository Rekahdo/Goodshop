package com.rekahdo.goodshop.messaging_service.controllers;

import com.rekahdo.goodshop.messaging_service.services.MessageService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/message/send")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService service;

    @PostMapping(path = "/account-created-email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendAcctCreatedEmail(@Valid @RequestParam @Email String email) {
        service.sendAcctCreatedEmail(email);
    }

    @PostMapping(path = "/account-verified-email")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PreAuthorize("#email == authentication.principal.email Or hasRole('ADMIN')")
    public void sendAccountVerified(@Valid @RequestParam @Email String email) {
        service.sendAccountVerified(email);
    }

}
