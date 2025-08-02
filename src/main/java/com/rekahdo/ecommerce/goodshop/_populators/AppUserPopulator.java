package com.rekahdo.ecommerce.goodshop._populators;

import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import com.rekahdo.ecommerce.goodshop._repository.AppUserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Component
@DependsOn("productPopulator")
@Profile("dev")
public class AppUserPopulator {

    @Autowired
    private AppUserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void insertIntoRepository(){
        repository.save(new AppUser("rekahdo", passwordEncoder.encode("ppppPP@1"), "okaforrichard76@gmail.com", LocalDate.now()));
        repository.save(new AppUser("john", passwordEncoder.encode("ppppPP@1"), "johnbillion@gmail.com", LocalDate.now()));
        repository.save(new AppUser("mary", passwordEncoder.encode("ppppPP@1"), "marynwankwo@gmail.com", LocalDate.now()));
        repository.save(new AppUser("paul", passwordEncoder.encode("ppppPP@1"), "paul@gmail.com", LocalDate.now()));
        repository.save(new AppUser("andrew", passwordEncoder.encode("ppppPP@1"), "andrew@gmail.com", LocalDate.now()));
        repository.save(new AppUser("david", passwordEncoder.encode("ppppPP@1"), "dav@gmail.com", LocalDate.now()));
        repository.save(new AppUser("alice", passwordEncoder.encode("ppppPP@1"), "alice@gmail.com", LocalDate.now()));
        repository.save(new AppUser("angela", passwordEncoder.encode("ppppPP@1"), "angela@gmail.com", LocalDate.now()));
        repository.save(new AppUser("ben", passwordEncoder.encode("ppppPP@1"), "ben@gmail.com", LocalDate.now()));
        repository.save(new AppUser("joe", passwordEncoder.encode("ppppPP@1"), "joe@gmail.com", LocalDate.now()));
    }

}