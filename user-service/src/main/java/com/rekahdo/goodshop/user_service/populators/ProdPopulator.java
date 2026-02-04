package com.rekahdo.goodshop.user_service.populators;

import com.rekahdo.goodshop.user_service.entities.AppUser;
import com.rekahdo.goodshop.user_service.repositories.AppUserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@RequiredArgsConstructor
public class ProdPopulator {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    private void insert() {
        if (!appUserRepository.findAll().isEmpty())
            return;

        appUserRepository.save(new AppUser(
                "admin", passwordEncoder.encode("admin"),
                "javadevrekahdo@gmail.com", true));
    }

}
