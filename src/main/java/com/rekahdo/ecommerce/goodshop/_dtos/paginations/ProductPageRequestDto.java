package com.rekahdo.ecommerce.goodshop._dtos.paginations;

import com.rekahdo.ecommerce.goodshop._controllers.AppUserController;
import com.rekahdo.ecommerce.goodshop._controllers.ProductController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.ProductDto;
import com.rekahdo.ecommerce.goodshop._entities.Product;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public final class ProductPageRequestDto extends PageRequestDto<Product, ProductDto>{

	public ProductPageRequestDto() {
		setSize(5);
	}

}