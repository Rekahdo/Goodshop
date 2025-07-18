package com.rekahdo.ecommerce.goodshop._dtos;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonFilter("profileDtoFilter")
public class ProfileDto extends EntityDto<ProfileDto> {

	private String bio;

	private String phoneNumber;

	private LocalDate dateOfBirth;

	private Long userId;

}