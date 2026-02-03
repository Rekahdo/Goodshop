package com.rekahdo.goodshop.phone_service.mappers;

import com.rekahdo.goodshop.phone_service.dtos.clients.PhoneClient;
import com.rekahdo.goodshop.phone_service.dtos.entities.PhoneDto;
import com.rekahdo.goodshop.phone_service.dtos.request.PhoneRequest;
import com.rekahdo.goodshop.phone_service.entities.Phone;
import com.rekahdo.goodshop.phone_service.enums.PhonePurpose;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PhoneMapper extends EntityMapper<Phone, PhoneRequest, PhoneDto, PhoneClient> {

    @Override
    @Mapping(target = "purpose", ignore = true)
    Phone toEntity(PhoneRequest request);

    @Override
    @Mapping(target = "purpose", ignore = true)
    PhoneDto toDto(Phone entity);

    @Override
    @Mapping(target = "purpose", ignore = true)
    PhoneClient toClient(Phone entity);

    @Override
    @AfterMapping
    default void afterMappingToDto(@MappingTarget PhoneDto target, Phone source) {
        String phone = source.getNumber();
        String code = source.getCode().getNumber();

        target.setCountryCode(code);
        target.setValidNumber(String.format("+%s%s", code, phone));
        target.setPurpose(PhonePurpose.findByIndex(source.getPurpose()));
    }

    @Override
    @AfterMapping
    default void afterMappingToClient(@MappingTarget PhoneClient target, Phone source) {
        String phone = source.getNumber();
        String code = source.getCode().getNumber();

        target.setCountryCode(code);
        target.setValidNumber(String.format("+%s%s", code, phone));
        target.setPurpose(PhonePurpose.findByIndex(source.getPurpose()).name());
    }

}