package com.rekahdo.goodshop.vendor_service.repositories;

import com.rekahdo.goodshop.vendor_service.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    @Query("SELECT cp FROM Contact cp WHERE id = (SELECT contact.id FROM Vendor v WHERE userId = ?1)")
    Optional<Contact> findByUserId(Long userId);

    @Modifying @Transactional
    @Query("UPDATE Contact SET phoneAdded = ?2 WHERE id = (SELECT contact.id FROM Vendor v WHERE userId = ?1)")
    void setPhoneAdded(Long userId, boolean added);

    @Modifying @Transactional
    @Query("UPDATE Contact SET emergencyPhoneAdded = ?2 WHERE id = (SELECT contact.id FROM Vendor v WHERE userId = ?1)")
    void setEmergencyPhoneAdded(Long userId, boolean added);

    @Modifying @Transactional
    @Query("UPDATE Contact SET idProofAdded = ?2 WHERE id = (SELECT contact.id FROM Vendor v WHERE userId = ?1)")
    void setIdProofAdded(Long userId, boolean added);

}