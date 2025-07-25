package com.rekahdo.ecommerce.goodshop._services;


import com.rekahdo.ecommerce.goodshop._controllers.ProductController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.CategoryDto;
import com.rekahdo.ecommerce.goodshop._dtos.entities.ProductDto;
import com.rekahdo.ecommerce.goodshop._entities.Category;
import com.rekahdo.ecommerce.goodshop._entities.Product;
import com.rekahdo.ecommerce.goodshop._mappers.CategoryMapper;
import com.rekahdo.ecommerce.goodshop._mappers.ProductMapper;
import com.rekahdo.ecommerce.goodshop._repository.CategoryRepository;
import com.rekahdo.ecommerce.goodshop._repository.ProductRepository;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.EmptyListException;
import com.rekahdo.ecommerce.goodshop.mappingJacksonValue.CategoryMJV;
import com.rekahdo.ecommerce.goodshop.mappingJacksonValue.ProductMJV;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private ProductMapper mapper;

    public ResponseEntity<?> createProduct(ProductDto dto) {
        Product product = mapper.toEntity(dto);
        product.setCategory(new Category(dto.getCategoryId()));
        repo.save(product);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editProduct(ProductDto dto) {

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getProducts() {
        List<Product> productList = repo.findAll();
        if(productList.isEmpty())
            throw new EmptyListException();

        List<ProductDto> dtos = mapper.toDtoList(productList);
        return ResponseEntity.ok(ProductMJV.filter(dtos));
    }

    public ResponseEntity<?> getProduct(Long productId) {

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteProduct(Long productId) {

        return ResponseEntity.ok().build();
    }
}
