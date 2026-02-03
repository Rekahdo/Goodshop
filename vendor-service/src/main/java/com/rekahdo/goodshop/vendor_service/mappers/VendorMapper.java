package com.rekahdo.goodshop.vendor_service.mappers;

import com.rekahdo.goodshop.vendor_service.dtos.entities.VendorDto;
import com.rekahdo.goodshop.vendor_service.entities.Vendor;
import com.rekahdo.goodshop.vendor_service.enums.ApprovalStatus;
import org.mapstruct.*;

import java.util.Objects;

@Mapper(componentModel = "spring", uses = {BusinessMapper.class, ContactMapper.class,
        BankMapper.class, UnresolvedMapper.class})
public interface VendorMapper extends CrossMapper<Vendor, VendorDto> {

    @Override
    @Mappings({
        @Mapping(target = "approvalStatus", ignore = true),
        @Mapping(target = "contactPerson", source = "contact"),
    })
    VendorDto toSecond(Vendor entity);

    @Override
    @AfterMapping
    default void afterMappingToSecond(@MappingTarget VendorDto target, Vendor source) {
        target.setApproved(Objects.equals(ApprovalStatus.APPROVED.index, source.getApprovalStatus()));
        target.setApprovalStatus(ApprovalStatus.findByIndex(source.getApprovalStatus()));
    }

}