package com.rekahdo.ecommerce.goodshop._controllers;

import com.rekahdo.ecommerce.goodshop._dtos.entities.AddressDto;
import com.rekahdo.ecommerce.goodshop._dtos.entities.CategoryDto;
import com.rekahdo.ecommerce.goodshop._dtos.paginations.CategoryPageRequestDto;
import com.rekahdo.ecommerce.goodshop._services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity
@RequestMapping(path = "/api/v1/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDITOR')")
	@PostMapping("")
	public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDto dto){
		return service.createCategory(dto);
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDITOR')")
	@PutMapping("/{categoryId}")
	public ResponseEntity<?> editCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryDto dto){
		return service.editCategory(categoryId, dto);
	}

	@GetMapping("")
	public ResponseEntity<?> getCategories(@ModelAttribute CategoryPageRequestDto dto){
		return service.getCategories(dto);
	}

	@GetMapping("/{categoryId}")
	public ResponseEntity<?> getCategory(@PathVariable Long categoryId){
		return service.getCategory(categoryId);
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDITOR')")
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<?> deleteCategory(@PathVariable Long categoryId){
		return service.deleteCategory(categoryId);
	}

}
