package com.rekahdo.goodshop.vendor_service.repositories;

import com.rekahdo.goodshop.vendor_service.entities.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    @Query("SELECT b FROM Bank b WHERE id = (SELECT bank.id FROM Vendor v WHERE userId = ?1)")
    Optional<Bank> findByUserId(Long userId);

    @Modifying @Transactional
    @Query("UPDATE Bank SET addressAdded = ?2 WHERE id = (SELECT bank.id FROM Vendor v WHERE userId = ?1)")
    void setAddressAdded(Long userId, boolean added);

}