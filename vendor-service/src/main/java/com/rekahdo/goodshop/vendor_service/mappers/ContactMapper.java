package com.rekahdo.goodshop.vendor_service.mappers;

import com.rekahdo.goodshop.vendor_service.dtos.entities.BusinessDto;
import com.rekahdo.goodshop.vendor_service.dtos.entities.ContactDto;
import com.rekahdo.goodshop.vendor_service.dtos.request.BusinessRequest;
import com.rekahdo.goodshop.vendor_service.dtos.request.ContactRequest;
import com.rekahdo.goodshop.vendor_service.entities.Business;
import com.rekahdo.goodshop.vendor_service.entities.Contact;
import com.rekahdo.goodshop.vendor_service.enums.Role;
import com.rekahdo.goodshop.vendor_service.enums.Title;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ContactMapper extends EntityMapper<Contact, ContactRequest, ContactDto> {

    @Override
    @Mappings({
        @Mapping(target = "title", ignore = true),
        @Mapping(target = "role", ignore = true),
    })
    Contact toEntity(ContactRequest contactRequest);

    @Override
    @Mappings({
            @Mapping(target = "title", ignore = true),
            @Mapping(target = "role", ignore = true)
    })
    ContactDto toDto(Contact entity);

    @Override
    @Mappings({
            @Mapping(target = "title", ignore = true),
            @Mapping(target = "role", ignore = true)
    })
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(ContactRequest source, @MappingTarget Contact target);

    @Override
    @AfterMapping
    default void afterMappingToEntity(@MappingTarget Contact target, ContactRequest source) {
        target.setTitle(source.title().index);
        target.setRole(source.role().index);
    }

    @Override
    @AfterMapping
    default void afterMappingToDto(@MappingTarget ContactDto target, Contact source) {
        target.setTitle(Title.findByIndex(source.getTitle()));
        target.setRole(Role.findByIndex(source.getRole()));
    }
}