package com.rekahdo.goodshop.profile_service.mappers;

import com.rekahdo.goodshop.profile_service.dtos.clients.ProfileClient;
import com.rekahdo.goodshop.profile_service.dtos.entities.ProfileDto;
import com.rekahdo.goodshop.profile_service.dtos.request.ProfileRequest;
import com.rekahdo.goodshop.profile_service.entities.Profile;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-04T08:26:19+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class ProfileMapperImpl implements ProfileMapper {

    @Override
    public Profile toEntity(ProfileRequest request) {
        if ( request == null ) {
            return null;
        }

        Profile profile = new Profile();

        beforeMappingToEntity( profile, request );

        profile.setFirstName( request.firstName() );
        profile.setMiddleName( request.middleName() );
        profile.setLastName( request.lastName() );

        afterMappingToEntity( profile, request );

        return profile;
    }

    @Override
    public List<ProfileDto> toDtoList(List<Profile> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ProfileDto> list = new ArrayList<ProfileDto>( entities.size() );
        for ( Profile profile : entities ) {
            list.add( toDto( profile ) );
        }

        return list;
    }

    @Override
    public List<Profile> toEntityList(List<ProfileRequest> requests) {
        if ( requests == null ) {
            return null;
        }

        List<Profile> list = new ArrayList<Profile>( requests.size() );
        for ( ProfileRequest profileRequest : requests ) {
            list.add( toEntity( profileRequest ) );
        }

        return list;
    }

    @Override
    public List<ProfileClient> toClientList(List<Profile> entities) {
        if ( entities == null ) {
            return null;
        }

        List<ProfileClient> list = new ArrayList<ProfileClient>( entities.size() );
        for ( Profile profile : entities ) {
            list.add( toClient( profile ) );
        }

        return list;
    }

    @Override
    public void updateEntity(ProfileRequest source, Profile target) {
        if ( source == null ) {
            return;
        }

        beforeMappingToEntity( target, source );

        if ( source.firstName() != null ) {
            target.setFirstName( source.firstName() );
        }
        if ( source.middleName() != null ) {
            target.setMiddleName( source.middleName() );
        }
        if ( source.lastName() != null ) {
            target.setLastName( source.lastName() );
        }

        afterMappingToEntity( target, source );
    }

    @Override
    public ProfileDto toDto(Profile entity) {
        if ( entity == null ) {
            return null;
        }

        ProfileDto profileDto = new ProfileDto();

        beforeMappingToDto( profileDto, entity );

        profileDto.setId( entity.getId() );
        profileDto.setFirstName( entity.getFirstName() );
        profileDto.setMiddleName( entity.getMiddleName() );
        profileDto.setLastName( entity.getLastName() );
        profileDto.setDateOfBirth( entity.getDateOfBirth() );
        profileDto.setUserId( entity.getUserId() );

        afterMappingToDto( profileDto, entity );

        return profileDto;
    }

    @Override
    public ProfileClient toClient(Profile entity) {
        if ( entity == null ) {
            return null;
        }

        ProfileClient profileClient = new ProfileClient();

        beforeMappingToClient( profileClient, entity );

        profileClient.setId( entity.getId() );
        profileClient.setUserId( entity.getUserId() );
        profileClient.setFirstName( entity.getFirstName() );
        profileClient.setMiddleName( entity.getMiddleName() );
        profileClient.setLastName( entity.getLastName() );
        profileClient.setDateOfBirth( entity.getDateOfBirth() );

        afterMappingToClient( profileClient, entity );

        return profileClient;
    }
}
