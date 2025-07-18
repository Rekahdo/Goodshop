package com.rekahdo.ecommerce.goodshop._repository;

import com.rekahdo.ecommerce.goodshop._entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Byte> {
}