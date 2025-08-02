package com.rekahdo.ecommerce.goodshop._mappers;

import com.rekahdo.ecommerce.goodshop._controllers.CategoryController;
import com.rekahdo.ecommerce.goodshop._controllers.ProductController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.ProductDto;
import com.rekahdo.ecommerce.goodshop._entities.Category;
import com.rekahdo.ecommerce.goodshop._entities.Product;
import org.mapstruct.*;

import java.math.BigDecimal;
import java.math.MathContext;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface ProductMapper extends Api_Mapper<Product, ProductDto> {

    @Override
    @Mappings({
        @Mapping(target = "price", ignore = true),
        @Mapping(target = "category", ignore = true)
    })
    ProductDto toDto(Product product);

    @Override
    @Mappings({
            @Mapping(target = "price", ignore = true),
            @Mapping(target = "category", ignore = true)
    })
    Product toEntity(ProductDto dto);

    @Override
    @Mappings({
            @Mapping(target = "price", ignore = true),
            @Mapping(target = "category", ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProductDto source, @MappingTarget Product target);

    @Override
    @AfterMapping
    default void afterMappingToDto(@MappingTarget ProductDto target, Product source){
        target.add(linkTo(methodOn(CategoryController.class).getCategory(source.getCategory().getId())).withRel("category"));
        target.setCategory(source.getCategory().getName());
        target.setPrice(source.getPrice().toString());
    }

    @Override
    @AfterMapping
    default void afterMappingToEntity(@MappingTarget Product target, ProductDto source) {
        target.setPrice(new BigDecimal(source.getPrice(), new MathContext(2)));
        target.setCategory(new Category(source.getCategoryId()));
    }

}
