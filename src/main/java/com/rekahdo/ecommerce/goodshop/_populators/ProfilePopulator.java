package com.rekahdo.ecommerce.goodshop._populators;

import com.rekahdo.ecommerce.goodshop._dtos.AppUserDto;
import com.rekahdo.ecommerce.goodshop._repository.AppUserRepository;
import com.rekahdo.ecommerce.goodshop._services.AppUserService;
import com.rekahdo.ecommerce.goodshop.enums.AuthorityRole;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@DependsOn("appUserPopulator")
@Profile({"dev", "prod"})
public class ProfilePopulator {

    private final AppUserService appUserService;
    private final boolean recordInserted;

    @Value("${adminKey}")
    private String adminKey;

    public ProfilePopulator(AppUserService appUserService, AppUserRepository appUserRepository) {
        this.appUserService = appUserService;
        recordInserted = appUserRepository.existsByUsername("rekahdo");
    }

    @PostConstruct
    private void createUsers(){
        if(!recordInserted){
            AppUserDto rekahdo = new AppUserDto("rekahdo", "ppppPP@1", "okaforrichard76@gmail.com")
                    .setRoles(AuthorityRole.ADMIN.getValue()).setAdminKey(adminKey);

            AppUserDto john = new AppUserDto("john", "ppppPP@1", "johnbillion@gmail.com")
                    .setRoles(AuthorityRole.MODERATOR.getValue()).setAdminKey(adminKey);

            AppUserDto mary = new AppUserDto("mary", "ppppPP@1", "marynwankwo@gmail.com")
                    .setRoles(AuthorityRole.EDITOR.getValue()).setAdminKey(adminKey);

            AppUserDto paul = new AppUserDto("paul", "ppppPP@1", "paul@gmail.com");

            appUserService.createUser(rekahdo);
            appUserService.createUser(john);
            appUserService.createUser(mary);
            appUserService.createUser(paul);
            appUserService.createUser(new AppUserDto("david", "ppppPP@1", null)
                    .setRoles(AuthorityRole.EDITOR.getValue()).setAdminKey(adminKey));
        }
    }

}
