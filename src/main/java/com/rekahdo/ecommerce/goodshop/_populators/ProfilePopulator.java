package com.rekahdo.ecommerce.goodshop._populators;

import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import com.rekahdo.ecommerce.goodshop._repository.ProfileRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Component
@DependsOn("appUserPopulator")
@Profile("dev")
public class ProfilePopulator {

    @Autowired
    private ProfileRepository repository;

    @PostConstruct
    public void insertIntoRepository(){
        String bio = "This is just a dummy bio for development purpose";
        repository.save(new com.rekahdo.ecommerce.goodshop._entities.Profile("richard", "tochukwu", "okafor", bio, true, "+234-9097114626", LocalDate.of(1999, 5, 6), new AppUser(1L)));
        repository.save(new com.rekahdo.ecommerce.goodshop._entities.Profile("john", "chukwuemeka", "okafor", bio, true, "+234-9030340615", LocalDate.of(1997, 6, 10), new AppUser(2L)));
        repository.save(new com.rekahdo.ecommerce.goodshop._entities.Profile("mary", "alessia", "nwankwo", bio, false, "+1-8045649213", LocalDate.of(1999, 7, 6), new AppUser(3L)));
    }

}
