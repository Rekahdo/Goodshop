package com.rekahdo.goodshop.otp_service.enums;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public enum OtpPurpose {

    ACCOUNT_VERIFICATION(1, "Account Verification OTP"),
    PROFILE_NUMBER_VERIFICATION(2, "Phone Number Verification OTP"),
    BUSINESS_NUMBER_VERIFICATION(3, "Business Phone Number Verification OTP"),
    BUSINESS_EMAIL_VERIFICATION(4, "Business Email Verification OTP"),
    CONTACT_PERSON_NUMBER_VERIFICATION(5, "Contact Person Phone Number Verification OTP"),
    CONTACT_PERSON_EMERGENCY_NUMBER_VERIFICATION(6, "Contact Person Emergency Phone Number Verification OTP"),
    FORGOT_PASSWORD(7, "Forgot Password OTP");

    public final Integer index;
    public final String title;

    OtpPurpose(Integer index, String title) {
        this.index = index;
        this.title = title;
    }

    public static OtpPurpose findByIndex(Integer purpose){
        Optional<OtpPurpose> optional = Arrays.stream(OtpPurpose.values())
                .filter(otpPurpose -> Objects.equals(otpPurpose.index, purpose))
                .findFirst();
        return optional.orElse(null);
    }


}