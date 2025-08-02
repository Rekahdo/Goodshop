package com.rekahdo.ecommerce.goodshop._populators;

import com.rekahdo.ecommerce.goodshop._dtos.entities.AuthorityDto;
import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import com.rekahdo.ecommerce.goodshop._entities.Authority;
import com.rekahdo.ecommerce.goodshop._repository.AuthorityRepository;
import com.rekahdo.ecommerce.goodshop._services.AuthorityService;
import com.rekahdo.ecommerce.goodshop.enums.AuthorityRole;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Component
@DependsOn("appUserPopulator")
@Profile("dev")
public class AuthorityPopulator {

    @Autowired
    private AuthorityRepository repository;

    @PostConstruct
    private void insertIntoRepository(){
        repository.save(new Authority(AuthorityRole.ADMIN, new AppUser(1L), LocalDate.now()));
        repository.save(new Authority(AuthorityRole.MODERATOR, new AppUser(2L), LocalDate.now()));
        repository.save(new Authority(AuthorityRole.EDITOR, new AppUser(3L), LocalDate.now()));
    }

}