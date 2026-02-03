package com.rekahdo.goodshop.address_service.repositories;

import com.rekahdo.goodshop.address_service.entities.Zipcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ZipcodeRepository extends JpaRepository<Zipcode, Long> {

    Optional<Zipcode> findByCode(Long code);

}