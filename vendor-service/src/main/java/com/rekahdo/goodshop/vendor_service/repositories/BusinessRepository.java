package com.rekahdo.goodshop.vendor_service.repositories;

import com.rekahdo.goodshop.vendor_service.entities.Bank;
import com.rekahdo.goodshop.vendor_service.entities.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    @Query("SELECT b FROM Business b WHERE id = (SELECT business.id FROM Vendor v WHERE userId = ?1)")
    Optional<Business> findByUserId(Long userId);

    @Modifying @Transactional
    @Query("UPDATE Business SET phoneAdded = ?2 WHERE id = (SELECT business.id FROM Vendor v WHERE userId = ?1)")
    void setPhoneAdded(Long userId, boolean added);

    @Modifying @Transactional
    @Query("UPDATE Business SET addressAdded = ?2 WHERE id = (SELECT business.id FROM Vendor v WHERE userId = ?1)")
    void setAddressAdded(Long userId, boolean added);

    @Modifying @Transactional
    @Query("UPDATE Business SET certificateAdded = ?2 WHERE id = (SELECT business.id FROM Vendor v WHERE userId = ?1)")
    void setCertificateAdded(Long userId, boolean added);

}