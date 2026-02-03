package com.rekahdo.goodshop.user_service.services;

import com.rekahdo.goodshop.user_service.controllers.AppUserController;
import com.rekahdo.goodshop.user_service.dtos.clients.AppUserClient;
import com.rekahdo.goodshop.user_service.dtos.request.Login;
import com.rekahdo.goodshop.user_service.entities.AppUser;
import com.rekahdo.goodshop.user_service.exceptions.classes.*;
import com.rekahdo.goodshop.user_service.feign.clients.AuthenticationServiceClient;
import com.rekahdo.goodshop.user_service.feign.dtos.JwtResponse;
import com.rekahdo.goodshop.user_service.mappers.AppUserMapper;
import com.rekahdo.goodshop.user_service.repositories.AppUserRepository;
import com.rekahdo.goodshop.user_service.security.config.AppUserDetails;
import com.rekahdo.goodshop.user_service.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class HiddenService {

    private final AppUserRepository repository;
    private final AppUserMapper mapper;

    private final AuthenticationManager authenticationManager;

    private final AuthenticationServiceClient authenticationService;

    private final PasswordEncoder passwordEncoder;

    public Map<String, URI> retrieveURIs(Long userId) {
        return Map.of(
                "retrieve-user", linkTo(methodOn(AppUserController.class).retrieveById(userId)).toUri()
        );
    }

    public JwtResponse login(Login request) {
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        Authentication authentication = authenticationManager.authenticate(usernamePassword);

        AppUserDetails appUserDetails = ((AppUserDetails) authentication.getPrincipal());
        List<String> roles = appUserDetails.getRoles();
        Map<String, ?> claims = Map.of("userId", appUserDetails.getId(), "email", appUserDetails.getEmail());
        return authenticationService.generateToken(appUserDetails.getUsername(), roles, claims, 24, ApiKey.AUTH_SERVICE);
    }

    public void resetPassword(String uidOrEmail, String password) {
        AppUser entity = repository.findByUidOrEmail(uidOrEmail)
                .orElseThrow(AccountNotFoundException::new);

        entity.setPassword(passwordEncoder.encode(password));
        repository.save(entity);
    }

    public AppUser findAndThrowById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public AppUser findAndThrowByEmail(String email) {
        return repository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email));
    }

    public void validate(Long userId) {
        AppUser user = findAndThrowById(userId);

        if(user.getId() == 1L)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    public void validate(String email) {
        AppUser user = findAndThrowByEmail(email);

        if(user.getId() == 1L)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
    }

    public void validateExistence(Long userId) {
        findAndThrowById(userId);
    }

    public void validateExistence(String email) {
        findAndThrowByEmail(email);
    }

    public AppUserClient returnValidated(Long userId) {
        AppUser user = findAndThrowById(userId);

        if(user.getId() == 1L)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return mapper.toClient(user);
    }

    public AppUserClient returnValidated(String email) {
        AppUser user = findAndThrowByEmail(email);

        if(user.getId() == 1L)
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        return mapper.toClient(user);
    }

    public AppUserClient returnValidatedExistence(Long userId) {
        AppUser entity = findAndThrowById(userId);
        return mapper.toClient(entity);
    }

    public AppUserClient returnValidatedExistence(String email) {
        AppUser entity = findAndThrowByEmail(email);
        return mapper.toClient(entity);
    }

}
