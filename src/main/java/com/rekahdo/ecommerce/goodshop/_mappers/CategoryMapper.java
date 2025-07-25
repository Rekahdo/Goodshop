package com.rekahdo.ecommerce.goodshop._mappers;

import com.rekahdo.ecommerce.goodshop._controllers.CategoryController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.CategoryDto;
import com.rekahdo.ecommerce.goodshop._entities.Category;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryController controller = new CategoryController();

    CategoryDto toDto(Category category);
    Category toEntity(CategoryDto categoryDto);
    List<CategoryDto> toDtoList(List<Category> categories);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(CategoryDto source, @MappingTarget Category target);

    @AfterMapping
    default void afterMappingToDto(@MappingTarget CategoryDto target, Category source){

    }

    @AfterMapping
    default void afterMappingToEntity(@MappingTarget Category target, CategoryDto source){

    }

}
