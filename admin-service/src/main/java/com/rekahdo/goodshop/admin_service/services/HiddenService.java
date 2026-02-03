package com.rekahdo.goodshop.admin_service.services;

import com.rekahdo.goodshop.admin_service.controllers.AdminController;
import com.rekahdo.goodshop.admin_service.entities.Admin;
import com.rekahdo.goodshop.admin_service.enums.Role;
import com.rekahdo.goodshop.admin_service.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.admin_service.feign.dtos.AccountPageRequest;
import com.rekahdo.goodshop.admin_service.repositories.AdminRepository;
import com.rekahdo.goodshop.admin_service.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class HiddenService {

    private final AdminRepository repository;
    private final UserServiceClient userService;

    public Map<String, URI> retrieveURIs(Long userId) {
        return Map.of(
//                "users", linkTo(methodOn(AdminController.class).retrieveAll(new AccountPageRequest())).toUri()
        );
    }

    public List<String> retrieveRoles(Long userId) {
        Admin admin = repository.findByUserId(userId).orElse(new Admin(userId, Role.USER));
        userService.validateExistence(userId, ApiKey.USER_SERVICE);
        return List.of(Role.findByIndex(admin.getRole()).name());
    }

}
