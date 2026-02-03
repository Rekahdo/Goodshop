package com.rekahdo.goodshop.user_service.populators;

import com.rekahdo.goodshop.user_service.entities.AppUser;
import com.rekahdo.goodshop.user_service.repositories.AppUserRepository;
import com.rekahdo.goodshop.user_service.utilities.UserIdGenerator;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Profile("!prod")
@RequiredArgsConstructor
public class AppUserPopulator {

    private final AppUserRepository repository;

    @PostConstruct
    private void insert() {
        if (!repository.findAll().isEmpty())
            return;

        repository.save(new AppUser("admin",
                new BCryptPasswordEncoder().encode("admin"),
                "javadevrekahdo@gmail.com", true));

        repository.save(new AppUser("rekahdo",
                new BCryptPasswordEncoder().encode("Passcode@1"),
                "okaforrichard76@gmail.com", true));

        repository.save(new AppUser("john",
                new BCryptPasswordEncoder().encode("Passcode@1"),
                "johnokafor@gmail.com", true));

        repository.save(new AppUser("mary",
                new BCryptPasswordEncoder().encode("Passcode@1"),
                "marynwankwo@gmail.com", false));

    }

}
