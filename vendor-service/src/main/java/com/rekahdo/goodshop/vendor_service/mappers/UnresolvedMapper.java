package com.rekahdo.goodshop.vendor_service.mappers;

import com.rekahdo.goodshop.vendor_service.dtos.entities.UnresolvedDto;
import com.rekahdo.goodshop.vendor_service.entities.Unresolved;
import com.rekahdo.goodshop.vendor_service.enums.UnresolvedReason;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = VendorMapper.class)
public interface UnresolvedMapper extends CrossMapper<Unresolved, UnresolvedDto> {

    @Override
    @Mapping(target = "reason", ignore = true)
    UnresolvedDto toSecond(Unresolved unresolved);

    @Override
    @AfterMapping
    default void afterMappingToSecond(@MappingTarget UnresolvedDto target, Unresolved source) {
        target.setReason(UnresolvedReason.findByIndex(source.getReason()));
        target.setMessage(UnresolvedReason.findByIndex(source.getReason()).message);
    }

}