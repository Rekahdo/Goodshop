package com.rekahdo.goodshop.user_service.mappers;

import com.rekahdo.goodshop.user_service.dtos.clients.AppUserClient;
import com.rekahdo.goodshop.user_service.dtos.entities.AppUserDto;
import com.rekahdo.goodshop.user_service.dtos.request.AccountRequest;
import com.rekahdo.goodshop.user_service.entities.AppUser;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-03T12:01:43+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class AppUserMapperImpl implements AppUserMapper {

    @Override
    public AppUser toEntity(AccountRequest request) {
        if ( request == null ) {
            return null;
        }

        AppUser appUser = new AppUser();

        beforeMappingToEntity( appUser, request );

        appUser.setUsername( request.username() );
        appUser.setPassword( request.password() );
        appUser.setEmail( request.email() );

        afterMappingToEntity( appUser, request );

        return appUser;
    }

    @Override
    public AppUserDto toDto(AppUser entity) {
        if ( entity == null ) {
            return null;
        }

        AppUserDto appUserDto = new AppUserDto();

        beforeMappingToDto( appUserDto, entity );

        appUserDto.setId( entity.getId() );
        appUserDto.setUid( entity.getUid() );
        appUserDto.setUsername( entity.getUsername() );
        appUserDto.setEmail( entity.getEmail() );
        appUserDto.setVerified( entity.isVerified() );
        if ( entity.getCreatedAt() != null ) {
            appUserDto.setCreatedAt( entity.getCreatedAt().toLocalDate() );
        }

        afterMappingToDto( appUserDto, entity );

        return appUserDto;
    }

    @Override
    public AppUserClient toClient(AppUser entity) {
        if ( entity == null ) {
            return null;
        }

        AppUserClient appUserClient = new AppUserClient();

        beforeMappingToClient( appUserClient, entity );

        appUserClient.setId( entity.getId() );
        appUserClient.setUid( entity.getUid() );
        appUserClient.setUsername( entity.getUsername() );
        appUserClient.setPassword( entity.getPassword() );
        appUserClient.setEmail( entity.getEmail() );
        appUserClient.setVerified( entity.isVerified() );
        if ( entity.getCreatedAt() != null ) {
            appUserClient.setCreatedAt( entity.getCreatedAt().toLocalDate() );
        }

        afterMappingToClient( appUserClient, entity );

        return appUserClient;
    }

    @Override
    public List<AppUserDto> toDtoList(List<AppUser> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AppUserDto> list = new ArrayList<AppUserDto>( entities.size() );
        for ( AppUser appUser : entities ) {
            list.add( toDto( appUser ) );
        }

        return list;
    }

    @Override
    public List<AppUser> toEntityList(List<AccountRequest> requests) {
        if ( requests == null ) {
            return null;
        }

        List<AppUser> list = new ArrayList<AppUser>( requests.size() );
        for ( AccountRequest accountRequest : requests ) {
            list.add( toEntity( accountRequest ) );
        }

        return list;
    }

    @Override
    public List<AppUserClient> toClientList(List<AppUser> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AppUserClient> list = new ArrayList<AppUserClient>( entities.size() );
        for ( AppUser appUser : entities ) {
            list.add( toClient( appUser ) );
        }

        return list;
    }

    @Override
    public void updateEntity(AccountRequest source, AppUser target) {
        if ( source == null ) {
            return;
        }

        beforeMappingToEntity( target, source );

        if ( source.username() != null ) {
            target.setUsername( source.username() );
        }
        if ( source.password() != null ) {
            target.setPassword( source.password() );
        }
        if ( source.email() != null ) {
            target.setEmail( source.email() );
        }

        afterMappingToEntity( target, source );
    }
}
