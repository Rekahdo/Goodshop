package com.rekahdo.goodshop.otp_service.dtos.entities;

import com.rekahdo.goodshop.otp_service.enums.OtpPurpose;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OtpDto {

    private Integer otp;
    private OtpPurpose purpose;
    private Long userId;
    private String sentTo;
    private boolean sentToEmail;
    private boolean verified;
    private LocalDateTime expireAt;

}
