package com.rekahdo.goodshop.address_service.repositories;

import com.rekahdo.goodshop.address_service.entities.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {

    Optional<Street> findByNameIgnoreCase(String name);

}