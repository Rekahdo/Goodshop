package com.rekahdo.ecommerce.goodshop._populators;

import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@DependsOn("productPopulator")
@Profile({"dev", "prod"})
public class CategoryPopulator {



}
