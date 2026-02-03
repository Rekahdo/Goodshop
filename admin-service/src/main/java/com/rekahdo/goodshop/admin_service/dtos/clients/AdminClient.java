package com.rekahdo.goodshop.admin_service.dtos.clients;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AdminClient implements ApiClient {

	private Long id;
	private Long userId;
	private String role;
	private LocalDate assignedAt;

}