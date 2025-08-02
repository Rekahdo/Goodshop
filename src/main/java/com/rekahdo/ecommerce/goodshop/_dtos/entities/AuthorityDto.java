package com.rekahdo.ecommerce.goodshop._dtos.entities;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.rekahdo.ecommerce.goodshop.enums.AuthorityRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonFilter("authorityDtoFilter")
public class AuthorityDto extends EntityDto<AuthorityDto> {

	@NotNull(message = "Role must contain a value")
	@Enumerated(EnumType.STRING)
	private AuthorityRole role;

	private LocalDate assignedAt;
	private String adminKey;

	public boolean adminKeyIsNotValid(String adminKey){
		return !adminKey.equals(this.adminKey);
	}

	public AuthorityDto(AuthorityRole role) {
		this.role = role;
	}

	public AuthorityDto(AuthorityRole role, String adminKey) {
		this.role = role;
		this.adminKey = adminKey;
	}
}