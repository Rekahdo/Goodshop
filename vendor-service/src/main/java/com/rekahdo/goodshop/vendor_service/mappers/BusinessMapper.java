package com.rekahdo.goodshop.vendor_service.mappers;

import com.rekahdo.goodshop.vendor_service.dtos.entities.BusinessDto;
import com.rekahdo.goodshop.vendor_service.dtos.request.BusinessRequest;
import com.rekahdo.goodshop.vendor_service.entities.Business;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessMapper extends EntityMapper<Business, BusinessRequest, BusinessDto> {

}