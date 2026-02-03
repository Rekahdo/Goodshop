package com.rekahdo.goodshop.vendor_service.repositories;

import com.rekahdo.goodshop.vendor_service.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {

    boolean existsByUserId(Long userId);

    Optional<Vendor> findByUserId(Long userId);

    @Modifying @Transactional
    @Query("UPDATE Vendor SET business = ?2, contact = ?3 WHERE userId = ?1")
    void setBusinessAndContactPersonId(Long userId, Long businessId, Long contactPersonId);

    @Modifying @Transactional
    @Query("UPDATE Vendor SET bank = ?2 WHERE userId = ?1")
    void setBankId(Long userId, Long bankId);

    @Transactional @Modifying
    @Procedure(procedureName = "delete_vendor")
    void deleteByUserId(Long user_id);

}