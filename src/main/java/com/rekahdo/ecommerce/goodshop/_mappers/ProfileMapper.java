package com.rekahdo.ecommerce.goodshop._mappers;

import com.rekahdo.ecommerce.goodshop._controllers.AppUserController;
import com.rekahdo.ecommerce.goodshop._dtos.entities.ProfileDto;
import com.rekahdo.ecommerce.goodshop._entities.Profile;
import com.rekahdo.ecommerce.goodshop.utilities.StringFormat;
import org.mapstruct.*;

import java.time.LocalDate;
import java.util.Date;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Mapper(componentModel = "spring")
public interface ProfileMapper extends Api_Mapper<Profile, ProfileDto> {

    @Override
    @Mappings({
            @Mapping(target = "phoneNumber", ignore = true),
            @Mapping(target = "dateOfBirth", ignore = true)
    })
    ProfileDto toDto(Profile profile);

    @Override
    @Mappings({
            @Mapping(target = "phoneNumber", ignore = true),
            @Mapping(target = "dateOfBirth", ignore = true)
    })
    Profile toEntity(ProfileDto dto);

    @Override
    @Mappings({
            @Mapping(target = "phoneNumber", ignore = true),
            @Mapping(target = "dateOfBirth", ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ProfileDto source, @MappingTarget Profile target);

    @Override
    @AfterMapping
    default void afterMappingToDto(@MappingTarget ProfileDto target, Profile source) {
        if(source.getPhoneNumber() != null) {
            String[] number = StringFormat.splitByHyphen(source.getPhoneNumber());
            target.setCountryCode(number[0]); target.setPhoneNumber(number[1]);
        }
        target.setUsername(source.getAppUser().getUsername());
        target.add(linkTo(methodOn(AppUserController.class).getUser(source.getAppUser().getId())).withRel("user"));

        if(source.getDateOfBirth() != null)
            target.setDateOfBirth(source.getDateOfBirth().toString());
    }

    @Override
    @AfterMapping
    default void afterMappingToEntity(@MappingTarget Profile target, ProfileDto source) {
        if(source.getCountryCode() != null && source.getPhoneNumber() != null)
            target.setPhoneNumber(String.format("%s-%s", source.getCountryCode(), source.getPhoneNumber()));

        if(source.getDateOfBirth() != null) {
            String[] date = StringFormat.splitByHyphen(source.getDateOfBirth());
            int year = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[2]);
            target.setDateOfBirth(LocalDate.of(year, month, day));
        }
    }
}
