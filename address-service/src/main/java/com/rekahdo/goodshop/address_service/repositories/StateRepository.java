package com.rekahdo.goodshop.address_service.repositories;

import com.rekahdo.goodshop.address_service.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {

    Optional<State> findByNameIgnoreCase(String name);

}