package com.rekahdo.goodshop.admin_service.populators;

import com.rekahdo.goodshop.admin_service.entities.Admin;
import com.rekahdo.goodshop.admin_service.enums.Role;
import com.rekahdo.goodshop.admin_service.repositories.AdminRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@RequiredArgsConstructor
public class ProdPopulator {

    private final AdminRepository repository;

    @PostConstruct
    private void insert() {
        if (repository.findAll().isEmpty()) {
            repository.save(new Admin(1L, Role.ADMIN));
        }
    }

}
