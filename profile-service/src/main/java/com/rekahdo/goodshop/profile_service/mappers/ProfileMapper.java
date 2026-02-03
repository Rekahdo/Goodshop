package com.rekahdo.goodshop.profile_service.mappers;

import com.rekahdo.goodshop.profile_service.dtos.clients.ProfileClient;
import com.rekahdo.goodshop.profile_service.dtos.entities.ProfileDto;
import com.rekahdo.goodshop.profile_service.dtos.request.ProfileRequest;
import com.rekahdo.goodshop.profile_service.entities.Profile;
import com.rekahdo.goodshop.profile_service.enums.Gender;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDate;
import java.time.Period;

@Mapper(componentModel = "spring")
public interface ProfileMapper extends EntityMapper<Profile, ProfileRequest, ProfileDto, ProfileClient> {

    @Override
    @Mapping(target = "gender", ignore = true)
    ProfileDto toDto(Profile entity);

    @Override
    @Mapping(target = "gender", ignore = true)
    ProfileClient toClient(Profile entity);

    @Override
    @AfterMapping
    default void afterMappingToDto(@MappingTarget ProfileDto target, Profile source) {
        if(source.getDateOfBirth() != null) {
            LocalDate now = LocalDate.now();
            LocalDate dob = source.getDateOfBirth();
            int age = Period.between(dob, now).getYears();

            target.setAge(age);
        }

        if(source.getGender() != 0)
            target.setGender(Gender.findByIndex(source.getGender()));
    }

    @Override
    @AfterMapping
    default void afterMappingToClient(@MappingTarget ProfileClient target, Profile source) {
        if(source.getDateOfBirth() != null) {
            LocalDate now = LocalDate.now();
            LocalDate dob = source.getDateOfBirth();
            int age = Period.between(dob, now).getYears();

            target.setAge(age);
        }

        if(source.getGender() != 0)
            target.setGender(Gender.findByIndex(source.getGender()).name());
    }
}