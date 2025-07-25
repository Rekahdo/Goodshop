package com.rekahdo.ecommerce.goodshop._mappers;

import com.rekahdo.ecommerce.goodshop._controllers.CategoryController;
import com.rekahdo.ecommerce.goodshop._controllers.ProductController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.ProductDto;
import com.rekahdo.ecommerce.goodshop._entities.Product;
import org.mapstruct.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring", uses = CategoryMapper.class)
public interface ProductMapper {

    ProductController controller = new ProductController();

    ProductDto toDto(Product product);
    Product toEntity(ProductDto productDto);
    List<ProductDto> toDtoList(List<Product> products);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProductDto source, @MappingTarget Product target);

    @AfterMapping
    default void afterMappingToDto(@MappingTarget ProductDto target, Product source){
        target.add(linkTo(methodOn(ProductController.class).getProduct(target.getId())).withSelfRel());
        target.add(linkTo(methodOn(CategoryController.class).getCategory(target.getCategory().getId())).withRel("category"));
    }

    @AfterMapping
    default void afterMappingToEntity(@MappingTarget Product target, ProductDto source){

    }

}
