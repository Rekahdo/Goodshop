package com.rekahdo.goodshop.otp_service.services;

import com.rekahdo.goodshop.otp_service.entities.Otp;
import com.rekahdo.goodshop.otp_service.enums.OtpPurpose;
import com.rekahdo.goodshop.otp_service.exceptions.classes.*;
import com.rekahdo.goodshop.otp_service.feign.clients.MessagingServiceClient;
import com.rekahdo.goodshop.otp_service.feign.clients.PhoneServiceClient;
import com.rekahdo.goodshop.otp_service.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.otp_service.feign.dtos.AppUserClient;
import com.rekahdo.goodshop.otp_service.feign.dtos.PhoneClient;
import com.rekahdo.goodshop.otp_service.feign.dtos.SendOtpToEmail;
import com.rekahdo.goodshop.otp_service.feign.enums.PhonePurpose;
import com.rekahdo.goodshop.otp_service.repositories.OtpRepository;
import com.rekahdo.goodshop.otp_service.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository repository;

    private final MessagingServiceClient messagingService;
    private final UserServiceClient userService;
    private final PhoneServiceClient phoneService;

    @Value("${otp.expire.minutes}")
    private Integer otpExpireMinutes;

    public void requestEmailVerification(String email, OtpPurpose otpPurpose) {
        AppUserClient client = userService.returnValidated(email, ApiKey.USER_SERVICE);
        Integer otp = generate();

//        messagingService.sendOtpToEmail(new SendOtpToEmail(otp, otpPurpose.title, email, otpExpireMinutes),
//                ApiKey.MESSAGING_SERVICE);

        saveOtp(otp, email, client.getId(), true, otpPurpose.index);

        throw new ResponseStatusException(HttpStatus.OK,
                String.format("Send %s '%d' to email '%s' functionality is under development", otpPurpose.title, otp, email));
    }

    public void requestEmailVerification(Long userId, OtpPurpose otpPurpose) {
        AppUserClient client = userService.returnValidated(userId, ApiKey.USER_SERVICE);
        Integer otp = generate();

//        messagingService.sendOtpToEmail(new SendOtpToEmail(otp, otpPurpose.title, client.getEmail(), otpExpireMinutes),
//                ApiKey.MESSAGING_SERVICE);

        saveOtp(otp, client.getEmail(), client.getId(), true, otpPurpose.index);

        throw new ResponseStatusException(HttpStatus.OK,
                String.format("Send %s '%d' to email '%s' functionality is under development", otpPurpose.title, otp, client.getEmail()));
    }

    public void requestPhoneNumberVerification(Long userId, OtpPurpose otpPurpose, PhonePurpose phonePurpose) {
        PhoneClient client = phoneService.retrieve(userId, phonePurpose, ApiKey.PHONE_SERVICE);
        Integer otp = generate();
        String sendTo = String.format("%s%s", client.getCountryCode(), client.getNumber());

//        messagingService.sendOtpToNumber(new SendOtpToNumber(otp, otpPurpose.title, client.getCountryCode(),
//                client.getNumber(), otpExpireMinutes), ApiKey.MESSAGING_SERVICE);

        saveOtp(otp, sendTo, client.getUserId(), false, otpPurpose.index);

        throw new ResponseStatusException(HttpStatus.OK,
                String.format("Send OTP '%d' to number '%s' functionality is under development", otp, sendTo));
    }

    private void saveOtp(Integer otpCode, String sentTo, Long userId, boolean sentToEmail, Integer purpose){
        Otp otp = repository.findByUserId(userId).orElse(new Otp(otpCode, userId, sentTo));

        otp.setOtp(otpCode);
        otp.setUserId(userId);
        otp.setSentTo(sentTo);
        otp.setPurpose(purpose);
        otp.setVerified(false);
        otp.setSentToEmail(sentToEmail);
        otp.setExpireAt(LocalDateTime.now().plusMinutes(otpExpireMinutes));
        repository.save(otp);
    }

    // Verify user OTP
    public void verifyOtp(Integer otpCode){
        Otp otp = repository.findByOtp(otpCode)
                .orElseThrow(() -> new OTPNotFoundException(otpCode));

        if(otp.isVerified())
            throw new OTPVerifiedException(otp);

        if(otp.isExpired())
            throw new OTPExpiredException(otp);

        otp.setVerified(true);
        repository.save(otp);
    }

    private Integer generate() {
        Random random = new Random();
        return random.nextInt(100_000, 999_999);
    }
}
