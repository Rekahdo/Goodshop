package com.rekahdo.goodshop.phone_service.mappers;

import com.rekahdo.goodshop.phone_service.dtos.clients.PhoneClient;
import com.rekahdo.goodshop.phone_service.dtos.entities.PhoneDto;
import com.rekahdo.goodshop.phone_service.dtos.request.PhoneRequest;
import com.rekahdo.goodshop.phone_service.entities.Phone;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-03T13:43:58+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class PhoneMapperImpl implements PhoneMapper {

    @Override
    public List<PhoneDto> toDtoList(List<Phone> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PhoneDto> list = new ArrayList<PhoneDto>( entities.size() );
        for ( Phone phone : entities ) {
            list.add( toDto( phone ) );
        }

        return list;
    }

    @Override
    public List<Phone> toEntityList(List<PhoneRequest> requests) {
        if ( requests == null ) {
            return null;
        }

        List<Phone> list = new ArrayList<Phone>( requests.size() );
        for ( PhoneRequest phoneRequest : requests ) {
            list.add( toEntity( phoneRequest ) );
        }

        return list;
    }

    @Override
    public List<PhoneClient> toClientList(List<Phone> entities) {
        if ( entities == null ) {
            return null;
        }

        List<PhoneClient> list = new ArrayList<PhoneClient>( entities.size() );
        for ( Phone phone : entities ) {
            list.add( toClient( phone ) );
        }

        return list;
    }

    @Override
    public void updateEntity(PhoneRequest source, Phone target) {
        if ( source == null ) {
            return;
        }

        beforeMappingToEntity( target, source );

        if ( source.number() != null ) {
            target.setNumber( source.number() );
        }

        afterMappingToEntity( target, source );
    }

    @Override
    public Phone toEntity(PhoneRequest request) {
        if ( request == null ) {
            return null;
        }

        Phone phone = new Phone();

        beforeMappingToEntity( phone, request );

        phone.setNumber( request.number() );

        afterMappingToEntity( phone, request );

        return phone;
    }

    @Override
    public PhoneDto toDto(Phone entity) {
        if ( entity == null ) {
            return null;
        }

        PhoneDto phoneDto = new PhoneDto();

        beforeMappingToDto( phoneDto, entity );

        phoneDto.setId( entity.getId() );
        phoneDto.setNumber( entity.getNumber() );
        phoneDto.setVerified( entity.isVerified() );
        phoneDto.setUserId( entity.getUserId() );

        afterMappingToDto( phoneDto, entity );

        return phoneDto;
    }

    @Override
    public PhoneClient toClient(Phone entity) {
        if ( entity == null ) {
            return null;
        }

        PhoneClient phoneClient = new PhoneClient();

        beforeMappingToClient( phoneClient, entity );

        phoneClient.setId( entity.getId() );
        phoneClient.setNumber( entity.getNumber() );
        phoneClient.setVerified( entity.isVerified() );
        phoneClient.setUserId( entity.getUserId() );

        afterMappingToClient( phoneClient, entity );

        return phoneClient;
    }
}
