package com.rekahdo.goodshop.address_service.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record AddressRequest(

        String name,
        String flatNo,

        @NotNull(message = "'houseNo' can not be null")
        Integer houseNo,

        @NotNull(message = "'street' can not be null")
        String street,

        String offStreet,
        String closestBusStop,

        @NotNull(message = "'lga' can not be null")
        String lga,

        @NotNull(message = "'city' can not be null")
        String city,

        @NotNull(message = "'state' can not be null")
        String state,

        @NotNull(message = "'country' can not be null")
        String country,

        @NotNull(message = "'zipcode' can not be null")
        Long zipcode

) implements ApiRequest{}
