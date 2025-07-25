package com.rekahdo.ecommerce.goodshop._populators;

import com.rekahdo.ecommerce.goodshop._dtos.entities.CategoryDto;
import com.rekahdo.ecommerce.goodshop._dtos.entities.ProductDto;
import com.rekahdo.ecommerce.goodshop._repository.AppUserRepository;
import com.rekahdo.ecommerce.goodshop._services.CategoryService;
import com.rekahdo.ecommerce.goodshop._services.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class CategoryPopulator {

    private final CategoryService service;

    public CategoryPopulator(CategoryService service) {
        this.service = service;
    }

    @PostConstruct
    private void createUsers(){
        service.createCategory(new CategoryDto("Phone"));
        service.createCategory(new CategoryDto("Laptop"));
        service.createCategory(new CategoryDto("Television"));
    }

}
