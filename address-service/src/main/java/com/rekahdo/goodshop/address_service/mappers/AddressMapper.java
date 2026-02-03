package com.rekahdo.goodshop.address_service.mappers;

import com.rekahdo.goodshop.address_service.controllers.AddressController;
import com.rekahdo.goodshop.address_service.dtos.clients.AddressClient;
import com.rekahdo.goodshop.address_service.dtos.entities.AddressDto;
import com.rekahdo.goodshop.address_service.dtos.request.AddressRequest;
import com.rekahdo.goodshop.address_service.entities.Address;
import com.rekahdo.goodshop.address_service.enums.AddressPurpose;
import org.mapstruct.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface AddressMapper extends EntityMapper<Address, AddressRequest, AddressDto, AddressClient>{

    @Override
    @Mappings({
            @Mapping(target = "purpose", ignore = true),
            @Mapping(target = "street", ignore = true),
            @Mapping(target = "offStreet", ignore = true),
            @Mapping(target = "busStop", ignore = true),
            @Mapping(target = "lga", ignore = true),
            @Mapping(target = "city", ignore = true),
            @Mapping(target = "state", ignore = true),
            @Mapping(target = "country", ignore = true),
            @Mapping(target = "zipcode", ignore = true)
    })
    Address toEntity(AddressRequest addressRequest);

    @Override
    @Mappings({
            @Mapping(target = "purpose", ignore = true),
            @Mapping(target = "street", ignore = true),
            @Mapping(target = "offStreet", ignore = true),
            @Mapping(target = "busStop", ignore = true),
            @Mapping(target = "lga", ignore = true),
            @Mapping(target = "city", ignore = true),
            @Mapping(target = "state", ignore = true),
            @Mapping(target = "country", ignore = true),
            @Mapping(target = "zipcode", ignore = true)
    })
    AddressDto toDto(Address address);

    @Override
    @Mappings({
            @Mapping(target = "purpose", ignore = true),
            @Mapping(target = "street", ignore = true),
            @Mapping(target = "offStreet", ignore = true),
            @Mapping(target = "busStop", ignore = true),
            @Mapping(target = "lga", ignore = true),
            @Mapping(target = "city", ignore = true),
            @Mapping(target = "state", ignore = true),
            @Mapping(target = "country", ignore = true),
            @Mapping(target = "zipcode", ignore = true)
    })
    AddressClient toClient(Address address);

    @Override
    @Mappings({
            @Mapping(target = "street", ignore = true),
            @Mapping(target = "offStreet", ignore = true),
            @Mapping(target = "busStop", ignore = true),
            @Mapping(target = "lga", ignore = true),
            @Mapping(target = "city", ignore = true),
            @Mapping(target = "state", ignore = true),
            @Mapping(target = "country", ignore = true),
            @Mapping(target = "zipcode", ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(AddressRequest source, @MappingTarget Address target);

    @Override
    @AfterMapping
    default void afterMappingToDto(@MappingTarget AddressDto target, Address source) {
        target.setPurpose(AddressPurpose.findByIndex(source.getPurpose()));
        target.setStreet(source.getStreet().getName());
        target.setOffStreet(source.getOffStreet() != null ? source.getOffStreet().getName() : null);
        target.setBusStop(source.getBusStop() != null ? source.getBusStop().getName() : null);
        target.setLga(source.getLga() != null ? source.getLga().getName() : null);
        target.setCity(source.getCity().getName());
        target.setState(source.getState().getName());
        target.setCountry(source.getCountry().getName());
        target.setZipcode(source.getZipcode().getCode());

        // name, flat no, house no, street, off-street, lga, city, state, country, zipcode
        target.setAddress(String.format("%s%s%s%s%s%s%s%s",
                target.getName() == null ? "" : String.format("%s, ", target.getName()),
                target.getFlatNo() == null ? "" : String.format("%s, ", target.getFlatNo()),
                String.format("No. %d, %s, ", target.getHouseNo(), target.getStreet()),
                target.getOffStreet() == null ? "" : String.format("Off %s, ", target.getOffStreet()),
                target.getLga() == null ? "" : String.format("%s, ", target.getLga()),
                String.format("%s, ", target.getCity()),
                String.format("%s State, ", target.getState()),
                String.format("%s.", target.getCountry())
        ));

    }

    @Override
    @AfterMapping
    default void afterMappingToClient(@MappingTarget AddressClient target, Address source) {
        target.setPurpose(AddressPurpose.findByIndex(source.getPurpose()).name());
        target.setStreet(source.getStreet().getName());
        target.setOffStreet(source.getOffStreet() != null ? source.getOffStreet().getName() : null);
        target.setBusStop(source.getBusStop() != null ? source.getBusStop().getName() : null);
        target.setLga(source.getLga() != null ? source.getLga().getName() : null);
        target.setCity(source.getCity().getName());
        target.setState(source.getState().getName());
        target.setCountry(source.getCountry().getName());
        target.setZipcode(source.getZipcode().getCode());
        target.setSelf(linkTo(methodOn(AddressController.class).retrieveAll(source.getUserId())).toUri());

        // name, flat no, house no, street, off-street, lga, city, state, country, zipcode
        target.setAddress(String.format("%s%s%s%s%s%s%s%s",
                target.getName() == null ? "" : String.format("%s, ", target.getName()),
                target.getFlatNo() == null ? "" : String.format("%s, ", target.getFlatNo()),
                String.format("No. %d, %s, ", target.getHouseNo(), target.getStreet()),
                target.getOffStreet() == null ? "" : String.format("Off %s, ", target.getOffStreet()),
                target.getLga() == null ? "" : String.format("%s, ", target.getLga()),
                String.format("%s, ", target.getCity()),
                String.format("%s State, ", target.getState()),
                String.format("%s.", target.getCountry())
        ));

    }

}