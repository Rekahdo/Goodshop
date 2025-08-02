package com.rekahdo.ecommerce.goodshop._populators;

import com.rekahdo.ecommerce.goodshop._dtos.entities.CategoryDto;
import com.rekahdo.ecommerce.goodshop._dtos.entities.ProductDto;
import com.rekahdo.ecommerce.goodshop._entities.Category;
import com.rekahdo.ecommerce.goodshop._repository.AppUserRepository;
import com.rekahdo.ecommerce.goodshop._repository.CategoryRepository;
import com.rekahdo.ecommerce.goodshop._services.CategoryService;
import com.rekahdo.ecommerce.goodshop._services.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class CategoryPopulator {

    @Autowired
    private CategoryRepository repository;

    @PostConstruct
    private void insertIntoRepository(){
        repository.save(new Category("Phone"));
        repository.save(new Category("Laptop"));
        repository.save(new Category("Television"));
        repository.save(new Category("Bag"));
        repository.save(new Category("Chair"));
        repository.save(new Category("House"));
        repository.save(new Category("Pet"));
        repository.save(new Category("Wide"));
        repository.save(new Category("Fan"));
        repository.save(new Category("Light"));
        repository.save(new Category("Door"));
        repository.save(new Category("Bed"));
        repository.save(new Category("Oven"));
        repository.save(new Category("Microwave"));
        repository.save(new Category("Box"));
        repository.save(new Category("Fridge"));
        repository.save(new Category("Table"));
        repository.save(new Category("Gas"));
        repository.save(new Category("Wristwatch"));
        repository.save(new Category("Bangle"));
    }

}
