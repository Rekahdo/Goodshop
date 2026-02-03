package com.rekahdo.goodshop.phone_service.dtos.request;

import com.rekahdo.goodshop.phone_service.validations.annotations.CountryCode;
import com.rekahdo.goodshop.phone_service.validations.annotations.Number;
import jakarta.validation.constraints.NotNull;

public record PhoneRequest(

        @CountryCode
        @NotNull(message = "'countryCode' can not be null")
        String countryCode,

        @Number
        @NotNull(message = "'number' can not be null")
        String number

) implements ApiRequest {
        public String phone(){
                return String.format("%s%s", countryCode, number);
        }
}
