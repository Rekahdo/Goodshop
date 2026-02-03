package com.rekahdo.goodshop.admin_service.mappers;

import com.rekahdo.goodshop.admin_service.dtos.clients.AdminClient;
import com.rekahdo.goodshop.admin_service.dtos.entities.AdminDto;
import com.rekahdo.goodshop.admin_service.dtos.request.AdminRequest;
import com.rekahdo.goodshop.admin_service.entities.Admin;
import com.rekahdo.goodshop.admin_service.enums.Role;
import org.mapstruct.*;

import java.time.LocalDate;

@Mapper(componentModel = "spring")
public interface AdminMapper extends EntityMapper<Admin, AdminRequest, AdminDto, AdminClient> {

    @Override
    @Mapping(target = "role", ignore = true)
    Admin toEntity(AdminRequest request);

    @Override
    @Mapping(target = "role", ignore = true)
    AdminClient toClient(Admin entity);

    @Override
    @Mapping(target = "role", ignore = true)
    AdminDto toDto(Admin entity);

    @Override
    @Mapping(target = "role", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(AdminRequest source, @MappingTarget Admin target);

    @Override
    @AfterMapping
    default void afterMappingToEntity(@MappingTarget Admin target, AdminRequest source) {
        Role role = Role.valueOf(source.role().trim().toUpperCase());
        target.setRole(role.index);
        target.setAssignedAt(LocalDate.now());
    }

    @Override
    default void afterMappingToDto(AdminDto target, Admin source) {
        target.setRole(Role.findByIndex(source.getRole()));
    }

    @Override
    default void afterMappingToClient(AdminClient target, Admin source) {
        target.setRole(Role.findByIndex(source.getRole()).name());
    }
}