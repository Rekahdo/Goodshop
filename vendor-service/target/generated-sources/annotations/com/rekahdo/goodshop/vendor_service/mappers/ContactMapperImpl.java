package com.rekahdo.goodshop.vendor_service.mappers;

import com.rekahdo.goodshop.vendor_service.dtos.entities.ContactDto;
import com.rekahdo.goodshop.vendor_service.dtos.request.ContactRequest;
import com.rekahdo.goodshop.vendor_service.entities.Contact;
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
public class ContactMapperImpl implements ContactMapper {

    @Override
    public List<ContactDto> toDtoList(List<Contact> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ContactDto> list = new ArrayList<ContactDto>( entities.size() );
        for ( Contact contact : entities ) {
            list.add( toDto( contact ) );
        }

        return list;
    }

    @Override
    public List<Contact> toEntityList(List<ContactRequest> requests) {
        if ( requests == null ) {
            return null;
        }

        List<Contact> list = new ArrayList<Contact>( requests.size() );
        for ( ContactRequest contactRequest : requests ) {
            list.add( toEntity( contactRequest ) );
        }

        return list;
    }

    @Override
    public Contact toEntity(ContactRequest contactRequest) {
        if ( contactRequest == null ) {
            return null;
        }

        Contact contact = new Contact();

        beforeMappingToEntity( contact, contactRequest );

        contact.setName( contactRequest.name() );

        afterMappingToEntity( contact, contactRequest );

        return contact;
    }

    @Override
    public ContactDto toDto(Contact entity) {
        if ( entity == null ) {
            return null;
        }

        ContactDto contactDto = new ContactDto();

        beforeMappingToDto( contactDto, entity );

        contactDto.setId( entity.getId() );
        contactDto.setName( entity.getName() );

        afterMappingToDto( contactDto, entity );

        return contactDto;
    }

    @Override
    public void updateEntity(ContactRequest source, Contact target) {
        if ( source == null ) {
            return;
        }

        beforeMappingToEntity( target, source );

        if ( source.name() != null ) {
            target.setName( source.name() );
        }

        afterMappingToEntity( target, source );
    }
}
