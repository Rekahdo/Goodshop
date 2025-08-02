package com.rekahdo.ecommerce.goodshop._services;

import com.rekahdo.ecommerce.goodshop._dtos.records.ApiEmail;
import com.rekahdo.ecommerce.goodshop._dtos.records.MailBody;
import com.rekahdo.ecommerce.goodshop._dtos.records.Otp;
import com.rekahdo.ecommerce.goodshop._dtos.records.Password;
import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import com.rekahdo.ecommerce.goodshop._entities.ForgotPassword;
import com.rekahdo.ecommerce.goodshop._repository.AppUserRepository;
import com.rekahdo.ecommerce.goodshop._repository.ForgotPasswordRepository;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.EmailNotFoundException;
import com.rekahdo.ecommerce.goodshop.exceptions.classes.OTPNotFoundException;
import com.rekahdo.ecommerce.goodshop.utilities.ApiLogger;
import com.rekahdo.ecommerce.goodshop.utilities.Time;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Transactional
@Service
public class ForgotPasswordService {

    @Autowired
    private ForgotPasswordRepository repository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OneTimePasswordService otpService;

    @Autowired
    private EmailService emailService;

    private final ApiLogger logger = new ApiLogger(getClass());

    public ResponseEntity<?> verifyEmail(ApiEmail email) {
        AppUser user = appUserRepository.findByEmail(email.email()).orElseThrow(
                () -> new EmailNotFoundException(email.email()));

        Integer otp = otpService.generate();

        MailBody mailBody = MailBody.builder()
                .to(user.getEmail())
                .subject("Forgot Password OTP")
                .text(String.format("Your OTP is '%d'.", otp))
                .build();

        ForgotPassword forgotPassword = new ForgotPassword(
                otp, user, LocalDateTime.now().plusSeconds(Time.THIRTY_SECONDS)
        );

        emailService.sendSimpleMessage(mailBody);
        repository.save(forgotPassword);
        logger.log(String.format("Forget Password record inserted for '%s'", email.email()));
        logger.log(String.format("Email verified and OTP sent to '%s'", email.email()));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> verifyOTP(Otp otp) {
        ForgotPassword fp = repository.findByOtpAndAppUser_Email(otp.otp(), otp.email()).orElseThrow(
                () -> new OTPNotFoundException(otp.otp(), otp.email()));

        if(fp.hasExpired()){
            repository.deleteById(fp.getId());
            logger.log(String.format("Forget Password record deleted for '%s'", otp.email()));
            return new ResponseEntity<>("OTP has expired", HttpStatus.EXPECTATION_FAILED);
        }

        repository.deleteById(fp.getId());
        logger.log(String.format("Forget Password record deleted for '%s'", otp.email()));
        logger.log(String.format("OTP Verified for '%s'", otp.email()));
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> resetPassword(Password password) {
        if(!Objects.equals(password.newPassword(), password.reEnterPassword()))
            return new ResponseEntity<>("Passwords do not match", HttpStatus.EXPECTATION_FAILED);

        String encodedPassword = passwordEncoder.encode(password.reEnterPassword());
        appUserRepository.updatePassword(encodedPassword, password.email());
        logger.log(String.format("User with email '%s' has reset their password", password.email()));
        return ResponseEntity.ok().build();
    }

}
