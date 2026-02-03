package com.rekahdo.goodshop.admin_service.dtos.entities;

import com.rekahdo.goodshop.admin_service.enums.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.net.URI;
import java.time.LocalDate;

@Getter
@Setter
public class AdminDto extends RepresentationModel<AdminDto> implements ApiDto {

	private Long id;
	private Role role;
	private LocalDate assignedAt;
	private URI user;

}