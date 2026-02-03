package com.rekahdo.goodshop.admin_service.mappers;

import com.rekahdo.goodshop.admin_service.dtos.clients.AdminClient;
import com.rekahdo.goodshop.admin_service.dtos.entities.AdminDto;
import com.rekahdo.goodshop.admin_service.dtos.request.AdminRequest;
import com.rekahdo.goodshop.admin_service.entities.Admin;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-03T12:02:30+0100",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.2 (Oracle Corporation)"
)
@Component
public class AdminMapperImpl implements AdminMapper {

    @Override
    public List<AdminDto> toDtoList(List<Admin> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AdminDto> list = new ArrayList<AdminDto>( entities.size() );
        for ( Admin admin : entities ) {
            list.add( toDto( admin ) );
        }

        return list;
    }

    @Override
    public List<Admin> toEntityList(List<AdminRequest> requests) {
        if ( requests == null ) {
            return null;
        }

        List<Admin> list = new ArrayList<Admin>( requests.size() );
        for ( AdminRequest adminRequest : requests ) {
            list.add( toEntity( adminRequest ) );
        }

        return list;
    }

    @Override
    public List<AdminClient> toClientList(List<Admin> entities) {
        if ( entities == null ) {
            return null;
        }

        List<AdminClient> list = new ArrayList<AdminClient>( entities.size() );
        for ( Admin admin : entities ) {
            list.add( toClient( admin ) );
        }

        return list;
    }

    @Override
    public Admin toEntity(AdminRequest request) {
        if ( request == null ) {
            return null;
        }

        Admin admin = new Admin();

        beforeMappingToEntity( admin, request );

        afterMappingToEntity( admin, request );

        return admin;
    }

    @Override
    public AdminClient toClient(Admin entity) {
        if ( entity == null ) {
            return null;
        }

        AdminClient adminClient = new AdminClient();

        beforeMappingToClient( adminClient, entity );

        adminClient.setId( entity.getId() );
        adminClient.setUserId( entity.getUserId() );
        adminClient.setAssignedAt( entity.getAssignedAt() );

        return adminClient;
    }

    @Override
    public AdminDto toDto(Admin entity) {
        if ( entity == null ) {
            return null;
        }

        AdminDto adminDto = new AdminDto();

        beforeMappingToDto( adminDto, entity );

        adminDto.setId( entity.getId() );
        adminDto.setAssignedAt( entity.getAssignedAt() );

        return adminDto;
    }

    @Override
    public void updateEntity(AdminRequest source, Admin target) {
        if ( source == null ) {
            return;
        }

        beforeMappingToEntity( target, source );

        afterMappingToEntity( target, source );
    }
}
