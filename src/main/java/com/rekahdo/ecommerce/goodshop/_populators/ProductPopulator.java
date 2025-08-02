package com.rekahdo.ecommerce.goodshop._populators;

import com.rekahdo.ecommerce.goodshop._dtos.entities.ProductDto;
import com.rekahdo.ecommerce.goodshop._entities.Category;
import com.rekahdo.ecommerce.goodshop._entities.Product;
import com.rekahdo.ecommerce.goodshop._repository.ProductRepository;
import com.rekahdo.ecommerce.goodshop._services.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
@DependsOn("categoryPopulator")
@Profile("dev")
public class ProductPopulator {

    @Autowired
    private ProductRepository repository;

    @PostConstruct
    private void insertIntoRepository(){
        // PHONES
        repository.save(new Product("Samsung Galaxy A71 5G", "A black samsung galaxy", new BigDecimal("350000", new MathContext(2)), new Category(1L)));
        repository.save(new Product("IPhone XS Max", "A gray IPhone xs max", new BigDecimal("650000", new MathContext(2)), new Category(1L)));
        repository.save(new Product("Nokia 3310", "A brand new Nokia 3310", new BigDecimal("50000", new MathContext(2)), new Category(1L)));

        // LAPTOPS
        repository.save(new Product("HP Pavillion", "Gray in color", new BigDecimal("500000", new MathContext(2)), new Category(2L)));
        repository.save(new Product("Dell XPS", "Black in color", new BigDecimal("650000", new MathContext(2)), new Category(2L)));
        repository.save(new Product("Macbook", "White in color", new BigDecimal("950000", new MathContext(2)), new Category(2L)));

        // TELEVISIONS
        repository.save(new Product("LG Flat TV", "Black TV", new BigDecimal("1350000", new MathContext(2)), new Category(3L)));
        repository.save(new Product("Samsung Flat TV", "White TV", new BigDecimal("2000000", new MathContext(2)), new Category(3L)));
        repository.save(new Product("Sony Flat TV", "Sony TV", new BigDecimal("1450000", new MathContext(2)), new Category(3L)));
    }

}
