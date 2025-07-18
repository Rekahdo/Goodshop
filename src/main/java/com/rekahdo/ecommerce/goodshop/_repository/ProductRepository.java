package com.rekahdo.ecommerce.goodshop._repository;

import com.rekahdo.ecommerce.goodshop._entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}