package com.rekahdo.goodshop.otp_service.mappers;

import com.rekahdo.goodshop.otp_service.dtos.entities.OtpDto;
import com.rekahdo.goodshop.otp_service.entities.Otp;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-03T13:47:16+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class OtpMapperImpl implements OtpMapper {

    @Override
    public Otp toEntity(OtpDto dto) {
        if ( dto == null ) {
            return null;
        }

        Otp otp = new Otp();

        beforeMappingToEntity( otp, dto );

        otp.setOtp( dto.getOtp() );
        if ( dto.getPurpose() != null ) {
            otp.setPurpose( dto.getPurpose().ordinal() );
        }
        otp.setUserId( dto.getUserId() );
        otp.setSentTo( dto.getSentTo() );
        otp.setSentToEmail( dto.isSentToEmail() );
        otp.setVerified( dto.isVerified() );
        otp.setExpireAt( dto.getExpireAt() );

        afterMappingToEntity( otp, dto );

        return otp;
    }

    @Override
    public List<OtpDto> toDtoList(List<Otp> entities) {
        if ( entities == null ) {
            return null;
        }

        List<OtpDto> list = new ArrayList<OtpDto>( entities.size() );
        for ( Otp otp : entities ) {
            list.add( toDto( otp ) );
        }

        return list;
    }

    @Override
    public void updateEntity(OtpDto source, Otp target) {
        if ( source == null ) {
            return;
        }

        beforeMappingToEntity( target, source );

        if ( source.getOtp() != null ) {
            target.setOtp( source.getOtp() );
        }
        if ( source.getPurpose() != null ) {
            target.setPurpose( source.getPurpose().ordinal() );
        }
        if ( source.getUserId() != null ) {
            target.setUserId( source.getUserId() );
        }
        if ( source.getSentTo() != null ) {
            target.setSentTo( source.getSentTo() );
        }
        target.setSentToEmail( source.isSentToEmail() );
        target.setVerified( source.isVerified() );
        if ( source.getExpireAt() != null ) {
            target.setExpireAt( source.getExpireAt() );
        }

        afterMappingToEntity( target, source );
    }

    @Override
    public OtpDto toDto(Otp otp) {
        if ( otp == null ) {
            return null;
        }

        OtpDto otpDto = new OtpDto();

        beforeMappingToDto( otpDto, otp );

        otpDto.setOtp( otp.getOtp() );
        otpDto.setUserId( otp.getUserId() );
        otpDto.setSentTo( otp.getSentTo() );
        otpDto.setSentToEmail( otp.isSentToEmail() );
        otpDto.setVerified( otp.isVerified() );
        otpDto.setExpireAt( otp.getExpireAt() );

        afterMappingToDto( otpDto, otp );

        return otpDto;
    }
}
