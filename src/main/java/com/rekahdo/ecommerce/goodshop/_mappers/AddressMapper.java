package com.rekahdo.ecommerce.goodshop._mappers;

import com.rekahdo.ecommerce.goodshop._controllers.AppUserController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.AddressDto;
import com.rekahdo.ecommerce.goodshop._dtos.entities.AppUserDto;
import com.rekahdo.ecommerce.goodshop._entities.Address;
import com.rekahdo.ecommerce.goodshop._entities.AppUser;
import com.rekahdo.ecommerce.goodshop.utilities.StringFormat;
import org.mapstruct.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface AddressMapper extends Api_Mapper<Address, AddressDto> {

    @Override
    @Mapping(target = "phoneNumber", ignore = true)
    AddressDto toDto(Address address);

    @Override
    @Mapping(target = "phoneNumber", ignore = true)
    Address toEntity(AddressDto dto);

    @Override
    @Mapping(target = "phoneNumber", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(AddressDto source, @MappingTarget Address target);

    @Override
    @AfterMapping
    default void afterMappingToDto(@MappingTarget AddressDto target, Address source) {
        if(source.getPhoneNumber() != null) {
            String[] number = StringFormat.splitByHyphen(source.getPhoneNumber());
            target.setCountryCode(number[0]); target.setPhoneNumber(number[1]);
        }

        target.add(linkTo(methodOn(AppUserController.class).getUser(source.getAppUser().getId())).withRel("user"));
    }

    @Override
    @AfterMapping
    default void afterMappingToEntity(@MappingTarget Address target, AddressDto source) {
        if(source.getCountryCode() != null && source.getPhoneNumber() != null)
            target.setPhoneNumber(String.format("%s-%s", source.getCountryCode(), source.getPhoneNumber()));
    }
}
