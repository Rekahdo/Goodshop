package com.rekahdo.goodshop.admin_service.feign.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Getter
@Setter
public class AppUserDto extends RepresentationModel<AppUserDto> {

	private Long id;
	private String uid;
	private String username;
	private String email;
	private boolean verified;
	private LocalDate createdAt;

}