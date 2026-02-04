package com.rekahdo.goodshop.vendor_service.dtos.mjv;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.rekahdo.goodshop.vendor_service.dtos.entities.VendorDto;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@JsonFilter("vendorFilter")
public class VendorMjv extends EntityDto<VendorDto> {

    public VendorMjv() {
        super("vendorFilter", Set.of("id", "name", "purpose", "_links"));
    }

}