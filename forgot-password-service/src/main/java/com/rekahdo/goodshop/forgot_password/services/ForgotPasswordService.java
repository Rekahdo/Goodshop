package com.rekahdo.goodshop.forgot_password.services;

import com.rekahdo.goodshop.forgot_password.dtos.request.ResetPassword;
import com.rekahdo.goodshop.forgot_password.feign.clients.OtpServiceClient;
import com.rekahdo.goodshop.forgot_password.feign.clients.UserServiceClient;
import com.rekahdo.goodshop.forgot_password.feign.dtos.AppUserClient;
import com.rekahdo.goodshop.forgot_password.utilities.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgotPasswordService {

    private final OtpServiceClient otpService;
    private final UserServiceClient userService;

    public void resetPassword(ResetPassword request) {
        AppUserClient user = userService.returnValidated(request.email(), ApiKey.USER_SERVICE);

        if(otpService.validateForgotPasswordOtp(user.getId(), request.email(), ApiKey.OTP_SERVICE)){
            userService.resetPassword(request.email(), request.password(), ApiKey.USER_SERVICE);
            otpService.deleteByEmail(request.email(), ApiKey.OTP_SERVICE);
        }
    }

}
