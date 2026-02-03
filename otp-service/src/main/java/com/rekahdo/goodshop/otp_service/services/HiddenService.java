package com.rekahdo.goodshop.otp_service.services;

import com.rekahdo.goodshop.otp_service.entities.Otp;
import com.rekahdo.goodshop.otp_service.enums.OtpPurpose;
import com.rekahdo.goodshop.otp_service.exceptions.classes.OTPNotFoundException;
import com.rekahdo.goodshop.otp_service.exceptions.classes.OTPNotVerifiedException;
import com.rekahdo.goodshop.otp_service.repositories.OtpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class HiddenService {

    private final OtpRepository repository;

    public boolean validate(String sentTo, Long userId, OtpPurpose purpose) {
        Otp otp = repository.findByUserIdAndSentTo(userId, sentTo)
                .orElseThrow(() -> new OTPNotFoundException(sentTo, purpose));

        if(!Objects.equals(otp.getPurpose(), purpose.index))
            throw new OTPNotFoundException(otp);

        if(!otp.isVerified())
            throw new OTPNotVerifiedException(otp);

        return true;
    }

    public void deleteByUserId(Long userId) {
        repository.deleteByUserId(userId);
    }

    public void deleteByOtp(Integer otp) {
        repository.deleteByOtp(otp);
    }

    public void deleteByEmail(String email) {
        repository.deleteBySentTo(email);
    }

}
