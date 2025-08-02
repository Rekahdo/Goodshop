package com.rekahdo.ecommerce.goodshop._dtos.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonFilter("addressDtoFilter")
public class AddressDto extends EntityDto<AddressDto> {

    @NotNull(message = "Specify name for this address")
    private String name;

    @NotNull(message = "Specify house number")
    private Integer houseNo;

    @NotNull(message = "Specify street name")
    private String street;

    @NotNull(message = "Specify city name")
    private String city;

    private String zipCode;

    @NotNull(message = "Specify state name")
    private String state;

    @Pattern(regexp = "^\\+\\d{1,3}$",
            message = "Country code should start with a '+' sign and end with 1 to 3 digits")
    private String countryCode;

    @Pattern(regexp = "^[1-9]\\d{9}$",
            message = "Phone number should not start with '0' and should contain 10 digits only")
    private String phoneNumber;

}