package com.rekahdo.ecommerce.goodshop._services;

import com.rekahdo.ecommerce.goodshop._controllers.CategoryController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.CategoryDto;
import com.rekahdo.ecommerce.goodshop._dtos.paginations.CategoryPageRequestDto;
import com.rekahdo.ecommerce.goodshop._entities.Category;
import com.rekahdo.ecommerce.goodshop._mappers.CategoryMapper;
import com.rekahdo.ecommerce.goodshop._repository.CategoryRepository;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.DataNotFoundException;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.EmptyListException;
import com.rekahdo.ecommerce.goodshop.mappingJacksonValue.CategoryMJV;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    @Autowired
    private CategoryMapper mapper;

    @Autowired
    private CategoryMJV mjv;

    public ResponseEntity<?> createCategory(CategoryDto dto) {
        repo.save(mapper.toEntity(dto));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editCategory(Long categoryId, CategoryDto dto) {
        Optional<Category> optional = repo.findById(categoryId);
        if(optional.isEmpty()) throw new DataNotFoundException(categoryId);

        mapper.updateEntity(dto, optional.get());
        repo.save(optional.get());
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getCategories(CategoryPageRequestDto dto) {
        Page<Category> categories = repo.findAll(dto.getPageable());
        if(categories.isEmpty())
            throw new EmptyListException();

        Page<CategoryDto> categoryDtos = categories.map(category -> {
            CategoryDto categoryDto = mapper.toDto(category);
            categoryDto.add(linkTo(methodOn(CategoryController.class).getCategory(category.getId())).withSelfRel());
            return categoryDto;
        });
        PagedModel<CategoryDto> pagedModel = dto.getPagedModel(categoryDtos, methodOn(CategoryController.class).getCategories(null));
        return ResponseEntity.ok(mjv.listFilter(pagedModel));
    }

    public ResponseEntity<?> getCategory(Long categoryId) {
        Optional<Category> optional = repo.findById(categoryId);
        if(optional.isEmpty()) throw new DataNotFoundException(categoryId);

        CategoryDto dto = mapper.toDto(optional.get());
        dto.add(linkTo(methodOn(CategoryController.class).getCategories(null)).withRel("categories"));
        return ResponseEntity.ok(mjv.selfFilter(dto));
    }

    public ResponseEntity<?> deleteCategory(Long categoryId) {
        repo.deleteById(categoryId);
        return ResponseEntity.ok().build();
    }

}
