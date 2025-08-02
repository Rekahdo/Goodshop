package com.rekahdo.ecommerce.goodshop._dtos.paginations;

import com.rekahdo.ecommerce.goodshop._controllers.CategoryController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.CategoryDto;
import com.rekahdo.ecommerce.goodshop._entities.Category;

public final class CategoryPageRequestDto extends PageRequestDto<Category, CategoryDto>{

	public CategoryPageRequestDto() {
		setSize(5);
	}

}