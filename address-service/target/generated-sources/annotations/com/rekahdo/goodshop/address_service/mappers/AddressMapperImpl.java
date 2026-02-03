package com.rekahdo.goodshop.address_service.mappers;

import com.rekahdo.goodshop.address_service.dtos.clients.AddressClient;
import com.rekahdo.goodshop.address_service.dtos.entities.AddressDto;
import com.rekahdo.goodshop.address_service.dtos.request.AddressRequest;
import com.rekahdo.goodshop.address_service.entities.Address;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-03T10:59:44+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public List<AddressDto> toDtoList(List<Address> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AddressDto> list = new ArrayList<AddressDto>( entities.size() );
        for ( Address address : entities ) {
            list.add( toDto( address ) );
        }

        return list;
    }

    @Override
    public List<Address> toEntityList(List<AddressRequest> requests) {
        if ( requests == null ) {
            return null;
        }

        List<Address> list = new ArrayList<Address>( requests.size() );
        for ( AddressRequest addressRequest : requests ) {
            list.add( toEntity( addressRequest ) );
        }

        return list;
    }

    @Override
    public List<AddressClient> toClientList(List<Address> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AddressClient> list = new ArrayList<AddressClient>( entities.size() );
        for ( Address address : entities ) {
            list.add( toClient( address ) );
        }

        return list;
    }

    @Override
    public Address toEntity(AddressRequest addressRequest) {
        if ( addressRequest == null ) {
            return null;
        }

        Address address = new Address();

        beforeMappingToEntity( address, addressRequest );

        address.setName( addressRequest.name() );
        address.setFlatNo( addressRequest.flatNo() );
        address.setHouseNo( addressRequest.houseNo() );

        afterMappingToEntity( address, addressRequest );

        return address;
    }

    @Override
    public AddressDto toDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressDto addressDto = new AddressDto();

        beforeMappingToDto( addressDto, address );

        addressDto.setId( address.getId() );
        addressDto.setName( address.getName() );
        addressDto.setFlatNo( address.getFlatNo() );
        addressDto.setHouseNo( address.getHouseNo() );
        addressDto.setUserId( address.getUserId() );

        afterMappingToDto( addressDto, address );

        return addressDto;
    }

    @Override
    public AddressClient toClient(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressClient addressClient = new AddressClient();

        beforeMappingToClient( addressClient, address );

        addressClient.setId( address.getId() );
        addressClient.setName( address.getName() );
        addressClient.setFlatNo( address.getFlatNo() );
        addressClient.setHouseNo( address.getHouseNo() );
        addressClient.setUserId( address.getUserId() );

        afterMappingToClient( addressClient, address );

        return addressClient;
    }

    @Override
    public void updateEntity(AddressRequest source, Address target) {
        if ( source == null ) {
            return;
        }

        beforeMappingToEntity( target, source );

        if ( source.name() != null ) {
            target.setName( source.name() );
        }
        if ( source.flatNo() != null ) {
            target.setFlatNo( source.flatNo() );
        }
        if ( source.houseNo() != null ) {
            target.setHouseNo( source.houseNo() );
        }

        afterMappingToEntity( target, source );
    }
}
