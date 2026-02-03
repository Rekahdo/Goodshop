package com.rekahdo.goodshop.admin_service.services;

import com.rekahdo.goodshop.admin_service.controllers.AdminController;
import com.rekahdo.goodshop.admin_service.dtos.request.AdminRequest;
import com.rekahdo.goodshop.admin_service.entities.Admin;
import com.rekahdo.goodshop.admin_service.enums.Role;
import com.rekahdo.goodshop.admin_service.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.admin_service.feign.dtos.AccountPageRequest;
import com.rekahdo.goodshop.admin_service.feign.dtos.AppUserDto;
import com.rekahdo.goodshop.admin_service.mappers.AdminMapper;
import com.rekahdo.goodshop.admin_service.repositories.AdminRepository;
import com.rekahdo.goodshop.admin_service.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository repository;

    public void assign(Long userId, Role role) {
        Admin admin = repository.findByUserId(userId).orElse(new Admin(userId, role));

        if(admin.getId() != null) {
            admin.setRole(role.index);
            admin.setAssignedAt(LocalDate.now());
        }

        repository.save(admin);
    }

    public void delete(Long userId) {
        repository.deleteByUserId(userId);
    }

}
