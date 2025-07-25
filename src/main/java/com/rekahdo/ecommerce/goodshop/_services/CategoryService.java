package com.rekahdo.ecommerce.goodshop._services;

import com.rekahdo.ecommerce.goodshop._dtos.entities.CategoryDto;
import com.rekahdo.ecommerce.goodshop._entities.Category;
import com.rekahdo.ecommerce.goodshop._mappers.CategoryMapper;
import com.rekahdo.ecommerce.goodshop._repository.CategoryRepository;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.EmptyListException;
import com.rekahdo.ecommerce.goodshop.mappingJacksonValue.CategoryMJV;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository repo;

    @Autowired
    private CategoryMapper mapper;

    public ResponseEntity<?> createCategory(CategoryDto dto) {
        repo.save(mapper.toEntity(dto));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> editCategory(CategoryDto dto) {

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> getCategories() {
        List<Category> categoryList = repo.findAll();
        if(categoryList.isEmpty())
            throw new EmptyListException();

        List<CategoryDto> dtos = mapper.toDtoList(categoryList);
        return ResponseEntity.ok(CategoryMJV.filter(dtos));
    }

    public ResponseEntity<?> getCategory(Long categoryId) {

        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> deleteCategory(Long categoryId) {

        return ResponseEntity.ok().build();
    }

}
