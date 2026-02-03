package com.rekahdo.goodshop.forgot_password.feign.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AppUserClient {

	private Long id;
	private String uid;
	private String username;
	private String password;
	private String email;
	private boolean verified;
	private LocalDate createdAt;

}