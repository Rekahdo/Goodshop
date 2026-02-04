package com.rekahdo.goodshop.vendor_service.mappers;

import com.rekahdo.goodshop.vendor_service.dtos.entities.BusinessDto;
import com.rekahdo.goodshop.vendor_service.dtos.request.BusinessRequest;
import com.rekahdo.goodshop.vendor_service.entities.Business;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-03T13:40:26+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class BusinessMapperImpl implements BusinessMapper {

    @Override
    public Business toEntity(BusinessRequest request) {
        if ( request == null ) {
            return null;
        }

        Business business = new Business();

        beforeMappingToEntity( business, request );

        business.setRegistrationName( request.registrationName() );
        business.setTradingName( request.tradingName() );
        business.setEmail( request.email() );

        afterMappingToEntity( business, request );

        return business;
    }

    @Override
    public BusinessDto toDto(Business entity) {
        if ( entity == null ) {
            return null;
        }

        BusinessDto businessDto = new BusinessDto();

        beforeMappingToDto( businessDto, entity );

        businessDto.setId( entity.getId() );
        businessDto.setRegistrationName( entity.getRegistrationName() );
        businessDto.setTradingName( entity.getTradingName() );
        businessDto.setEmail( entity.getEmail() );
        businessDto.setEmailVerified( entity.isEmailVerified() );

        afterMappingToDto( businessDto, entity );

        return businessDto;
    }

    @Override
    public List<BusinessDto> toDtoList(List<Business> entities) {
        if ( entities == null ) {
            return null;
        }

        List<BusinessDto> list = new ArrayList<BusinessDto>( entities.size() );
        for ( Business business : entities ) {
            list.add( toDto( business ) );
        }

        return list;
    }

    @Override
    public List<Business> toEntityList(List<BusinessRequest> requests) {
        if ( requests == null ) {
            return null;
        }

        List<Business> list = new ArrayList<Business>( requests.size() );
        for ( BusinessRequest businessRequest : requests ) {
            list.add( toEntity( businessRequest ) );
        }

        return list;
    }

    @Override
    public void updateEntity(BusinessRequest source, Business target) {
        if ( source == null ) {
            return;
        }

        beforeMappingToEntity( target, source );

        if ( source.registrationName() != null ) {
            target.setRegistrationName( source.registrationName() );
        }
        if ( source.tradingName() != null ) {
            target.setTradingName( source.tradingName() );
        }
        if ( source.email() != null ) {
            target.setEmail( source.email() );
        }

        afterMappingToEntity( target, source );
    }
}
