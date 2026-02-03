package com.rekahdo.goodshop.vendor_service.mappers;

import com.rekahdo.goodshop.vendor_service.dtos.entities.BankDto;
import com.rekahdo.goodshop.vendor_service.dtos.request.BankRequest;
import com.rekahdo.goodshop.vendor_service.entities.Bank;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BankMapper extends EntityMapper<Bank, BankRequest, BankDto> {

    @Override
    @Mapping(target = "accountType", ignore = true)
    Bank toEntity(BankRequest bankRequest);

    @Override
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(BankRequest source, @MappingTarget Bank target);

    @Override
    @AfterMapping
    default void afterMappingToEntity(@MappingTarget Bank target, BankRequest source) {
        target.setAccountType(source.accountType().getIndex());
    }

}