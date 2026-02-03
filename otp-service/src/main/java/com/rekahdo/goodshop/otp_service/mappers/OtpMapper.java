package com.rekahdo.goodshop.otp_service.mappers;

import com.rekahdo.goodshop.otp_service.dtos.entities.OtpDto;
import com.rekahdo.goodshop.otp_service.entities.Otp;
import com.rekahdo.goodshop.otp_service.enums.OtpPurpose;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OtpMapper extends Api_Mapper<Otp, OtpDto>{

    @Override
    @Mapping(target = "purpose", ignore = true)
    OtpDto toDto(Otp otp);

    @Override
    @AfterMapping
    default void afterMappingToDto(@MappingTarget OtpDto target, Otp source) {
        target.setPurpose(OtpPurpose.findByIndex(source.getPurpose()));
        target.setSentToEmail(source.isSentToEmail());
        target.setVerified(source.isVerified());
    }

}