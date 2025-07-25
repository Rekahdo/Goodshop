package com.rekahdo.ecommerce.goodshop._populators;

import com.rekahdo.ecommerce.goodshop._dtos.entities.ProductDto;
import com.rekahdo.ecommerce.goodshop._services.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;

@Component
@DependsOn("categoryPopulator")
@Profile("dev")
public class ProductPopulator {

    private final ProductService service;

    public ProductPopulator(ProductService service) {
        this.service = service;
    }

    @PostConstruct
    private void createUsers(){
        // PHONES
        service.createProduct(new ProductDto("Samsung Galaxy A71 5G", "A black samsung galaxy", new BigDecimal("350000", new MathContext(2)), (byte)1));
        service.createProduct(new ProductDto("IPhone XS Max", "A gray IPhone xs max", new BigDecimal("650000", new MathContext(2)), (byte)1));
        service.createProduct(new ProductDto("Nokia 3310", "A brand new Nokia 3310", new BigDecimal("50000", new MathContext(2)), (byte)1));

        // LAPTOPS
        service.createProduct(new ProductDto("HP Pavillion", "Gray in color", new BigDecimal("500000", new MathContext(2)), (byte)2));
        service.createProduct(new ProductDto("Dell XPS", "Black in color", new BigDecimal("650000", new MathContext(2)), (byte)2));
        service.createProduct(new ProductDto("Macbook", "White in color", new BigDecimal("950000", new MathContext(2)), (byte)2));

        // TELEVISIONS
        service.createProduct(new ProductDto("LG Flat TV", "Black TV", new BigDecimal("1350000", new MathContext(2)), (byte)3));
        service.createProduct(new ProductDto("Samsung Flat TV", "White TV", new BigDecimal("2000000", new MathContext(2)), (byte)3));
        service.createProduct(new ProductDto("Sony Flat TV", "Sony TV", new BigDecimal("1450000", new MathContext(2)), (byte)3));
    }

}
