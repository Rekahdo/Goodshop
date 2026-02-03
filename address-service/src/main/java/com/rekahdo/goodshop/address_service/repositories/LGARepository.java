package com.rekahdo.goodshop.address_service.repositories;

import com.rekahdo.goodshop.address_service.entities.LGA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LGARepository extends JpaRepository<LGA, Long> {

    Optional<LGA> findByNameIgnoreCase(String name);

}