package com.rekahdo.goodshop.otp_service.repositories;

import com.rekahdo.goodshop.otp_service.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp, Integer> {

    Optional<Otp> findByOtp(Integer otp);

    Optional<Otp> findByUserId(Long userId);

    Optional<Otp> findBySentTo(String sentTo);

    Optional<Otp> findByUserIdAndSentTo(Long userId, String sentTo);

    @Transactional @Modifying
    void deleteByOtp(Integer otp);

    @Transactional @Modifying
    void deleteByUserId(Long userId);

    @Transactional @Modifying
    @Query("DELETE FROM Otp WHERE sentTo = ?1")
    void deleteBySentTo(String sentTo);

}
