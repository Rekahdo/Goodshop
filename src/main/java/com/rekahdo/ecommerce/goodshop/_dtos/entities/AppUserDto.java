package com.rekahdo.ecommerce.goodshop._dtos.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.rekahdo.ecommerce.goodshop.validations.annotations.StrongPassword;
import com.rekahdo.ecommerce.goodshop.validations.annotations.Username;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonFilter("appUserDtoFilter")
public class AppUserDto extends EntityDto<AppUserDto> {

	@Username
	private String username;

	@StrongPassword
	private String password;

	@Email(message = "Enter a valid email format")
	private String email;

	private LocalDate createdAt;
	private LocalDate updatedAt;

	// HELPER CONSTRUCTORS
	public AppUserDto(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}

}