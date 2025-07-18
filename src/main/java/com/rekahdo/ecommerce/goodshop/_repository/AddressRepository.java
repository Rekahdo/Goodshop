package com.rekahdo.ecommerce.goodshop._repository;


import com.rekahdo.ecommerce.goodshop._entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}