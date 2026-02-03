package com.rekahdo.goodshop.user_service.populators;

import com.rekahdo.goodshop.user_service.entities.AppUser;
import com.rekahdo.goodshop.user_service.repositories.AppUserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
@RequiredArgsConstructor
public class ProdPopulator {

    private final AppUserRepository appUserRepository;

    @PostConstruct
    private void insert() {
        if (appUserRepository.findAll().isEmpty()) {
            appUserRepository.save(new AppUser("admin",
                    new BCryptPasswordEncoder().encode("admin12345"),
                    "javadevrekahdo@gmail.com", true));
        }
    }

}
