package com.rekahdo.goodshop.phone_service.repositories;

import com.rekahdo.goodshop.phone_service.entities.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {

    Optional<Code> findByNumber(String number);

    boolean existsByNumber(String number);
}