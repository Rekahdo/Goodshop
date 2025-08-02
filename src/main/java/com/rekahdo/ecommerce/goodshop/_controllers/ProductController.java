package com.rekahdo.ecommerce.goodshop._controllers;

import com.rekahdo.ecommerce.goodshop._dtos.entities.CategoryDto;
import com.rekahdo.ecommerce.goodshop._dtos.entities.ProductDto;
import com.rekahdo.ecommerce.goodshop._dtos.paginations.ProductPageRequestDto;
import com.rekahdo.ecommerce.goodshop._services.ProductService;
import com.rekahdo.ecommerce.goodshop._services.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableMethodSecurity
@RequestMapping(path = "/api/v1/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDITOR')")
	@PostMapping("")
	public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDto dto){
		return service.createProduct(dto);
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDITOR')")
	@PutMapping("/{productId}")
	public ResponseEntity<?> editProduct(@PathVariable Long productId, @Valid @RequestBody ProductDto dto){
		return service.editProduct(productId, dto);
	}

	@GetMapping("")
	public ResponseEntity<?> getProducts(@ModelAttribute ProductPageRequestDto dto,
			@RequestParam(required = false, defaultValue = "0") Long categoryId){
		return service.getProducts(dto, categoryId);
	}

	@GetMapping("/{productId}")
	public ResponseEntity<?> getProduct(@PathVariable Long productId){
		return service.getProduct(productId);
	}

	@PreAuthorize("hasRole('ADMIN') OR hasRole('EDITOR')")
	@DeleteMapping("/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long productId){
		return service.deleteProduct(productId);
	}

}
