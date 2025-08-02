package com.rekahdo.ecommerce.goodshop._repository;

import com.rekahdo.ecommerce.goodshop._entities.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {

    Optional<ForgotPassword> findByOtpAndAppUser_Email(Integer otp, String email);

}
