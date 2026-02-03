package com.rekahdo.goodshop.phone_service.repositories;

import com.rekahdo.goodshop.phone_service.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

    Optional<Phone> findByIdAndUserId(Long phoneId, Long userId);

    Optional<Phone> findByUserIdAndCode_NumberAndNumber(Long userId, String countryCode, String number);

    @Query("SELECT p FROM Phone p WHERE userId = ?1 AND CONCAT(code.number, number) = ?2")
    Optional<Phone> findByPhoneNumber(Long userId, String phoneNumber);

    List<Phone> findByUserId(Long userId);

    Optional<Phone> findByUserIdAndPurpose(Long userId, Integer purpose);

    List<Phone> findByUserIdAndPurposeIn(Long userId, List<Integer> purposes);

    @Modifying @Transactional
    @Query("UPDATE Phone SET verified = true WHERE userId = ?1 AND code.number = ?2 AND number = ?3")
    void verifyPhone(Long userId, String code, String number);

    @Modifying @Transactional
    @Query("UPDATE Phone SET verified = true WHERE userId = ?1 AND purpose = ?2")
    void verifyPhone(Long userId, Integer purpose);

    @Modifying @Transactional
    @Query("UPDATE Phone SET number = ?1 WHERE id = ?2")
    void updateNumber(String number, Long id);

    @Modifying @Transactional
    void deleteByUserIdAndPurpose(Long userId, Integer purpose);

    @Modifying @Transactional
    void deleteByUserIdAndPurposeIn(Long userId, List<Integer> purpose);

    @Modifying @Transactional
    @Query("DELETE FROM Phone WHERE id = ?1")
    void deletePhone(Long id);

    @Modifying @Transactional
    @Query("DELETE FROM Phone WHERE number = ?1 AND userId = ?2")
    void deletePhone(String number, Long userId);

    @Modifying @Transactional
    @Query("DELETE FROM Phone WHERE id = ?1 AND userId = ?2")
    void deletePhone(Long phoneId, Long userId);

}