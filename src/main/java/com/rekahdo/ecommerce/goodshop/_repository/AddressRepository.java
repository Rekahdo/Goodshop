package com.rekahdo.ecommerce.goodshop._repository;


import com.rekahdo.ecommerce.goodshop._entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findAllByAppUserId(Long userId);

    Optional<Address> findByIdAndAppUserId(Long addressId, Long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Address WHERE id = ?1 AND appUser.id = ?2")
    void deleteByIdAndAppUserId(Long addressId, Long userId);
}