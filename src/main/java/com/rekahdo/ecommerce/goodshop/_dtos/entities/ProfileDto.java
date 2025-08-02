package com.rekahdo.ecommerce.goodshop._dtos.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonFilter("profileDtoFilter")
public class ProfileDto extends EntityDto<ProfileDto> {

	private String firstName;
	private String middleName;
	private String lastName;
	private String username;
	private boolean male;
	private String bio;

	@Pattern(regexp = "^\\+\\d{1,3}$",
			message = "Country code should start with a '+' sign and end with 1 to 3 digits")
	private String countryCode;

	@Pattern(regexp = "^[1-9]\\d{9}$",
			message = "Phone number should not start with '0' and should contain 10 digits only")
	private String phoneNumber;

	@Pattern(regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[1,2][0-9]|3[0-1])$",
			message = "Date format should be 'YYYY-MM-DD'. E.g '1999-12-25'")
	private String dateOfBirth;

}