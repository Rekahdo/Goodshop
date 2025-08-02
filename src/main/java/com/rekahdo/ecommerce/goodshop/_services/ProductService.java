package com.rekahdo.ecommerce.goodshop._services;


import com.rekahdo.ecommerce.goodshop._controllers.AppUserController;
import com.rekahdo.ecommerce.goodshop._controllers.ProductController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.ProductDto;
import com.rekahdo.ecommerce.goodshop._dtos.paginations.ProductPageRequestDto;
import com.rekahdo.ecommerce.goodshop._entities.Category;
import com.rekahdo.ecommerce.goodshop._entities.Product;
import com.rekahdo.ecommerce.goodshop._mappers.ProductMapper;
import com.rekahdo.ecommerce.goodshop._repository.ProductRepository;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.DataNotFoundException;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.EmptyListException;
import com.rekahdo.ecommerce.goodshop.mappingJacksonValue.ProductMJV;
import com.rekahdo.ecommerce.goodshop.utilities.ApiLogger;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private ProductMapper mapper;

    @Autowired
    private ProductMJV mjv;

    private final ApiLogger logger = new ApiLogger(getClass());

    public ResponseEntity<?> createProduct(ProductDto dto) {
        repo.save(mapper.toEntity(dto));
        logger.log(String.format("Product '%s' has been added", dto.getName()));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editProduct(Long productId, ProductDto dto) {
        Optional<Product> optional = repo.findById(productId);
        if(optional.isEmpty()) throw new DataNotFoundException(productId);

        Product product = optional.get();
        mapper.updateEntity(dto, product);
        repo.save(product);

        logger.log(String.format("Product '%s' was edited", dto.getName()));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getProducts(ProductPageRequestDto dto, Long categoryId) {
        List<Product> productList = (categoryId == 0 ? repo.findAll() : repo.findAllByCategoryId(categoryId));
        if(productList.isEmpty())
            throw new EmptyListException();

        Page<ProductDto> pageDto = dto.getPagedList(productList).map(product -> {
            ProductDto productDto = mapper.toDto(product);
            productDto.add(linkTo(methodOn(ProductController.class).getProduct(product.getId())).withSelfRel());
            return productDto;
        });
        PagedModel<ProductDto> pagedModel = dto.getPagedModel(pageDto, methodOn(ProductController.class).getProducts(null, categoryId));
        return ResponseEntity.ok(mjv.listFilter(pagedModel));
    }

    public ResponseEntity<?> getProduct(Long productId) {
        Optional<Product> optional = repo.findById(productId);
        if(optional.isEmpty()) throw new DataNotFoundException(productId);

        ProductDto dto = mapper.toDto(optional.get());
        dto.add(linkTo(methodOn(ProductController.class).getProducts(null, 0L)).withRel("products"));
        return ResponseEntity.ok(mjv.selfFilter(dto));
    }

    public ResponseEntity<?> deleteProduct(Long productId) {
        repo.deleteById(productId);
        logger.log(String.format("Product ID '%d' has been deleted", productId));
        return ResponseEntity.ok().build();
    }
}
