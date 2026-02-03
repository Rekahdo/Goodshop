package com.rekahdo.goodshop.user_service.dtos.clients;

import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.time.LocalDate;

@Getter
@Setter
public class AppUserClient implements ApiClient {

	private Long id;
	private String uid;
	private String username;
	private String password;
	private String email;
	private URI self;
	private boolean verified;
	private LocalDate createdAt;

}