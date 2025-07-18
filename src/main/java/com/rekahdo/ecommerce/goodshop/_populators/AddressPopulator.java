package com.rekahdo.ecommerce.goodshop._populators;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@DependsOn("appUserPopulator")
@Profile({"dev", "prod"})
public class AddressPopulator {

    @PostConstruct
    public void insert(){

    }

}
