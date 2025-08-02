package com.rekahdo.ecommerce.goodshop._populators;

import com.rekahdo.ecommerce.goodshop._entities.Address;
import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import com.rekahdo.ecommerce.goodshop._repository.AddressRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@DependsOn("appUserPopulator")
@Profile("dev")
public class AddressPopulator {

    @Autowired
    private AddressRepository repository;

    @PostConstruct
    public void insertIntoRepository(){
        repository.save(new Address("Home", 25, "Shotayo Hughes Street", "Surulere", "Lagos", "+234-9097114626", new AppUser(1L)));
        repository.save(new Address("Brother's Home", 12, "Ikenne Street", "Surulere", "Lagos", "+234-9030340615", new AppUser(1L)));
        repository.save(new Address("Cousin's Home", 7, "Obafemi Awolowo Way", "Ikeja", "Lagos", "+1-8045649213", new AppUser(1L)));
        repository.save(new Address("Home", 12, "Ikenne Street", "Surulere", "Lagos", "+234-9030340615", new AppUser(2L)));
        repository.save(new Address("Home", 7, "Obafemi Awolowo Way", "Ikeja", "Lagos", "+1-8045649213", new AppUser(3L)));
    }

}
