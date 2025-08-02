package com.rekahdo.ecommerce.goodshop._mappers;

import com.rekahdo.ecommerce.goodshop._controllers.CategoryController;
import com.rekahdo.ecommerce.goodshop._controllers.ProductController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.CategoryDto;
import com.rekahdo.ecommerce.goodshop._entities.Category;
import org.mapstruct.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface CategoryMapper  extends Api_Mapper<Category, CategoryDto>{

    @Override
    @AfterMapping
    default void afterMappingToDto(@MappingTarget CategoryDto target, Category source) {
        target.add(linkTo(methodOn(ProductController.class).getProducts(null, source.getId())).withRel("products"));
    }

}
