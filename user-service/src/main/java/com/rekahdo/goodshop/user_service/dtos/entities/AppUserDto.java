package com.rekahdo.goodshop.user_service.dtos.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

@Getter
@Setter
@Relation(collectionRelation = "users", itemRelation = "user")
public class AppUserDto implements ApiDto {

	private Long id;
	private String uid;
	private String username;
	private String email;
	private boolean verified;
	private LocalDate createdAt;

}